package com.example.kibanaoperator

import com.example.kibanaoperator.EphemeralKibanaDeploymentResource.Companion.COMPONENT
import io.fabric8.kubernetes.api.model.EnvVarBuilder
import io.fabric8.kubernetes.api.model.LabelSelector
import io.fabric8.kubernetes.api.model.LabelSelectorBuilder
import io.fabric8.kubernetes.api.model.ObjectMeta
import io.fabric8.kubernetes.api.model.PodSpec
import io.fabric8.kubernetes.api.model.PodSpecBuilder
import io.fabric8.kubernetes.api.model.PodTemplateSpec
import io.fabric8.kubernetes.api.model.PodTemplateSpecBuilder
import io.fabric8.kubernetes.api.model.apps.Deployment
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder
import io.fabric8.kubernetes.api.model.apps.DeploymentSpec
import io.fabric8.kubernetes.api.model.apps.DeploymentSpecBuilder
import io.javaoperatorsdk.operator.api.reconciler.Context
import io.javaoperatorsdk.operator.api.reconciler.ResourceIDMatcherDiscriminator
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.CRUDKubernetesDependentResource
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.KubernetesDependent
import io.javaoperatorsdk.operator.processing.event.ResourceID
import java.util.function.Function

@KubernetesDependent(resourceDiscriminator = EphemeralKibanaDiscriminator::class)
class EphemeralKibanaDeploymentResource(
    private var template: Deployment = BuilderHelper.loadTemplate(Deployment::class.java, "kibana-deployment.yaml"),
) : CRUDKubernetesDependentResource<Deployment, EphemeralKibanaResource>(Deployment::class.java) {
    override fun desired(
        primary: EphemeralKibanaResource?,
        context: Context<EphemeralKibanaResource>?,
    ): Deployment? {
        val meta: ObjectMeta = BuilderHelper.fromPrimary(primary, COMPONENT).build()

        return DeploymentBuilder(template)
            .withMetadata(meta)
            .withSpec(buildSpec(primary!!, meta))
            .build()
    }

    private fun buildSelector(labels: Map<String, String>): LabelSelector =
        LabelSelectorBuilder()
            .addToMatchLabels(labels)
            .build()

    private fun buildSpec(
        primary: EphemeralKibanaResource,
        primaryMeta: ObjectMeta,
    ): DeploymentSpec =
        DeploymentSpecBuilder()
            .withSelector(buildSelector(primaryMeta.labels))
            .withReplicas(primary.spec.replicas)
            .withTemplate(buildPodTemplate(primary, primaryMeta))
            .build()

    private fun buildPodTemplate(
        primary: EphemeralKibanaResource,
        primaryMeta: ObjectMeta,
    ): PodTemplateSpec =
        PodTemplateSpecBuilder()
            .withMetadata(primaryMeta)
            .withSpec(buildPodSpec(primary))
            .build()

    private fun buildPodSpec(primary: EphemeralKibanaResource): PodSpec {
        val imageVersion = primary.spec.version.ifBlank { Constants.DEFAULT_VERSION }
        val imageName = primary.spec.image.ifBlank { Constants.DEFAULT_IMAGE }
        return PodSpecBuilder(template.spec.template.spec)
            .editContainer(0)
            .withImage("$imageName:$imageVersion")
            .withEnv(
                EnvVarBuilder()
                    .withName("ELASTICSEARCH_HOSTS")
                    .withValue(primary.spec.elasticSearchUrl)
                    .build(),
                EnvVarBuilder()
                    .withName("XPACK_SECURITY_ENABLED")
                    .withValue(false.toString())
                    .build(),
            ).and()
            .build()
    }

    companion object {
        const val COMPONENT = "deployment"
    }
}

internal class EphemeralKibanaDiscriminator :
    ResourceIDMatcherDiscriminator<Deployment?, EphemeralKibanaResource?>(
        COMPONENT,
        Function<EphemeralKibanaResource?, ResourceID> { p ->
            ResourceID(
                p.metadata.name + "-" + COMPONENT,
                p.metadata.namespace,
            )
        },
    )
