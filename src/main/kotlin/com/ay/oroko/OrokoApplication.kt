package com.ay.oroko

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrokoApplication

fun main(args: Array<String>) {
    runApplication<OrokoApplication>(*args)
}
