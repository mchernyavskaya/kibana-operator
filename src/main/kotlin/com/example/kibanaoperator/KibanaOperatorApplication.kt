package com.example.kibanaoperator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KibanaOperatorApplication

fun main(args: Array<String>) {
    runApplication<KibanaOperatorApplication>(*args)
}
