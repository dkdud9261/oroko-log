package com.ay.oroko

import com.ay.oroko.api.repository.BoardRepository
import com.ay.oroko.common.domain.Board
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrokoApplication

fun main(args: Array<String>) {
    runApplication<OrokoApplication>(*args)
}
