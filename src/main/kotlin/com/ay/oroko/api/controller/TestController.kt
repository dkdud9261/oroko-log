package com.ay.oroko.api.controller

import com.ay.oroko.api.model.BoardRequest
import com.ay.oroko.api.model.BoardResponse
import com.ay.oroko.client.TestClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/test")
class TestController(
    private val testClient: TestClient
) {

    @GetMapping("/get")
    fun getTest(): Flux<BoardResponse> {
        return testClient.getBoardTest()
    }

    @GetMapping("/post")
    fun postTest(): Mono<BoardResponse> {
        return testClient.postBoardTest(BoardRequest("테스트제목", "/here"))
    }
}