package com.example.kibanaoperator

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder
import io.fabric8.kubernetes.client.CustomResource
import java.io.IOException
import java.io.InputStream

object BuilderHelper {
    private val mapper = ObjectMapper(YAMLFactory())

    fun <T : CustomResource<*, *>?> fromPrimary(
        primary: T,
        componentName: String,
    ): ObjectMetaBuilder {
        val name = primary!!.metadata.name.ifBlank { "${Constants.OPERATOR_NAME}-$componentName" }
        return ObjectMetaBuilder()
            .withNamespace(primary.metadata.namespace)
            .addToLabels("name", name)
            .addToLabels("component", componentName)
            .withName(name)
            .addToLabels("ManagedBy", Constants.OPERATOR_NAME)
    }

    fun <T> loadTemplate(
        clazz: Class<T>?,
        resource: String,
    ): T {
        var loader = Thread.currentThread().contextClassLoader
        if (loader == null) {
            loader = BuilderHelper::class.java.classLoader
        }
        try {
            loader!!.getResourceAsStream(resource).use { inputStream ->
                return loadTemplate(clazz, inputStream)
            }
        } catch (e: IOException) {
            throw RuntimeException("Unable to load classpath resource '" + resource + "': " + e.message)
        }
    }

    @Throws(IOException::class)
    fun <T> loadTemplate(
        clazz: Class<T>?,
        `is`: InputStream?,
    ): T {
        return mapper.readValue(`is`, clazz)
    }
}
