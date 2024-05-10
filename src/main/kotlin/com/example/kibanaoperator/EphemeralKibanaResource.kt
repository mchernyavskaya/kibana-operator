package com.example.kibanaoperator

import com.example.kibanaoperator.Constants.Companion.DEFAULT_IMAGE
import com.example.kibanaoperator.Constants.Companion.DEFAULT_VERSION
import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.Group
import io.fabric8.kubernetes.model.annotation.Version
import io.javaoperatorsdk.operator.api.ObservedGenerationAwareStatus

@Group("com.example")
@Version("v1")
class EphemeralKibanaResource: CustomResource<EphemeralKibanaSpec, EphemeralKibanaStatus>(), Namespaced {
    override fun getSpec(): EphemeralKibanaSpec {
        return super.getSpec() ?: EphemeralKibanaSpec()
    }

    override fun getStatus(): EphemeralKibanaStatus {
        return super.getStatus() ?: EphemeralKibanaStatus()
    }
}

data class EphemeralKibanaSpec (
    val replicas: Int = 1,
    val image: String = DEFAULT_IMAGE,
    val version: String = DEFAULT_VERSION,
    val elasticsearchUrl: String = "http://elasticsearch:9200",
    val cleanUpOnShutdown: Boolean = false, // TODO: implement this feature
    val discoverVersion: Boolean = false // TODO: implement this feature
)

class EphemeralKibanaStatus: ObservedGenerationAwareStatus()