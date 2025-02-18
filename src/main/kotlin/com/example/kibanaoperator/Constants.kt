package com.example.kibanaoperator

interface Constants {
    companion object {
        const val OPERATOR_NAME: String = "ephemeral-kibana-operator"
        const val DEFAULT_IMAGE: String = "kibana"
        const val DEFAULT_VERSION: String = "8.17.2"
    }
}
