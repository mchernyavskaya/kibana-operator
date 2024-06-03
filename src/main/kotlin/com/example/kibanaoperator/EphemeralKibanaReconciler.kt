package com.example.kibanaoperator

import io.javaoperatorsdk.operator.api.reconciler.Context
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration
import io.javaoperatorsdk.operator.api.reconciler.Reconciler
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent
import org.springframework.stereotype.Component

@ControllerConfiguration(
    dependents = [
        Dependent(
            name = EphemeralKibanaDeploymentResource.COMPONENT,
            type = EphemeralKibanaDeploymentResource::class,
        ),
    ],
)
@Component
class EphemeralKibanaReconciler : Reconciler<EphemeralKibanaResource> {
    override fun reconcile(
        p0: EphemeralKibanaResource?,
        p1: Context<EphemeralKibanaResource>?,
    ): UpdateControl<EphemeralKibanaResource> {
        // TODO: implement the reconciliation logic
        return UpdateControl.noUpdate()
    }
}
