package com.example.kibanaoperator

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestKibanaOperatorApplication

fun main(args: Array<String>) {
    fromApplication<KibanaOperatorApplication>().with(TestKibanaOperatorApplication::class).run(*args)
}
