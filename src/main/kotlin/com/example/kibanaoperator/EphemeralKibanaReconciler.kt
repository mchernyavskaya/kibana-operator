package com.example.kibanaoperator

import io.javaoperatorsdk.operator.api.reconciler.Context
import io.javaoperatorsdk.operator.api.reconciler.Reconciler
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl

class EphemeralKibanaReconciler: Reconciler<EphemeralKibanaResource> {
    override fun reconcile(
        p0: EphemeralKibanaResource?,
        p1: Context<EphemeralKibanaResource>?
    ): UpdateControl<EphemeralKibanaResource> {
        // TODO: implement the reconciliation logic
        return UpdateControl.noUpdate()
    }
}