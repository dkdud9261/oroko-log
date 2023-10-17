package com.ay.oroko.api.controller

import com.ay.oroko.api.model.LogResponse
import com.ay.oroko.api.service.HomeService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
@WebFluxTest(HomeController::class)
class HomeControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var homeService: HomeService

    @Test
    fun 데일리로그조회_api_테스트() {
        val date = LocalDate.of(2023, 10, 1)

        `when`(homeService.getLog(date)).thenReturn(
            Mono.just(
                LogResponse(
                    10,
                    mutableMapOf(
                        1 to 1, 2 to 3, 10 to 123
                    )
                )
            )
        )

        client.get()
            .uri {
                it.path("/home/log")
                    .queryParam("year", 2023)
                    .queryParam("month", 10)
                    .build()
            }
            .exchange()
            .expectStatus().isOk
    }
}