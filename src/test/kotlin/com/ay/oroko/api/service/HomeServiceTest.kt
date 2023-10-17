package com.ay.oroko.api.service

import com.ay.oroko.common.domain.Board
import com.ay.oroko.common.domain.Shorts
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.time.LocalDate

@SpringBootTest
class HomeServiceTest {

    @Autowired lateinit var homeService: HomeService

    @MockBean lateinit var boardService: BoardService
    @MockBean lateinit var shortsService: ShortsService

    @Test
    fun 데일리_로그조회_테스트() {
        val date = LocalDate.now()

        `when`(boardService.getDailyBoardLog(date))
            .thenReturn(
                Flux.just(
                    Board(title = "title1", contentPath = "/here1"),
                    Board(title = "title2", contentPath = "/here2"),
                    Board(title = "title3", contentPath = "/here3"),
                )
            )

        `when`(shortsService.getDailyShortsLog(date))
            .thenReturn(
                Flux.just(
                    Shorts(content = "shortContent1"),
                    Shorts(content = "shortContent2")
                )
            )

        homeService.getLog(date)
            .`as`(StepVerifier::create)
            .expectNextMatches { it.logs == mutableMapOf(17 to 5) }
            .verifyComplete()
    }
}