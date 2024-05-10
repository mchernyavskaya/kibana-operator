package com.example.kibanaoperator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "io.javaoperatorsdk.operator.springboot.starter",
        "com.example.kibanaoperator"
    ]

)
class KibanaOperatorApplication

fun main(args: Array<String>) {
    runApplication<KibanaOperatorApplication>(*args)
}
