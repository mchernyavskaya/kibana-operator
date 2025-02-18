package com.example.kibanaoperator

import com.example.kibanaoperator.Constants.Companion.DEFAULT_IMAGE
import com.example.kibanaoperator.Constants.Companion.DEFAULT_VERSION
import io.fabric8.kubernetes.api.model.Namespaced
import io.fabric8.kubernetes.client.CustomResource
import io.fabric8.kubernetes.model.annotation.Group
import io.fabric8.kubernetes.model.annotation.ShortNames
import io.fabric8.kubernetes.model.annotation.Version
import io.javaoperatorsdk.operator.api.ObservedGenerationAwareStatus

@Group("com.example")
@Version("v1")
@ShortNames("ekr")
class EphemeralKibanaResource :
    CustomResource<EphemeralKibanaSpec, EphemeralKibanaStatus>(),
    Namespaced {
    override fun getSpec(): EphemeralKibanaSpec = super.getSpec() ?: EphemeralKibanaSpec()

    override fun getStatus(): EphemeralKibanaStatus = super.getStatus() ?: EphemeralKibanaStatus()
}

class EphemeralKibanaSpec {
    var replicas: Int = 1
    var image: String = DEFAULT_IMAGE
    var version: String = DEFAULT_VERSION
    var elasticSearchUrl: String = "http://elasticsearch:9200"
    var cleanUpOnShutdown: Boolean = false // TODO: implement this feature
    var discoverVersion: Boolean = false // TODO: implement this feature
}

class EphemeralKibanaStatus : ObservedGenerationAwareStatus()
