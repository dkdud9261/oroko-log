package com.ay.oroko.client

import com.ay.oroko.api.model.BoardRequest
import com.ay.oroko.api.model.BoardResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class TestClient {
    private val webClient:WebClient = WebClient.create("http://localhost:8080")

    fun getBoardTest(): Flux<BoardResponse> {
        return webClient.get()
            .uri("/board/list")
            .retrieve()
            .bodyToFlux(BoardResponse::class.java)
    }

    fun postBoardTest(boardRequest: BoardRequest): Mono<BoardResponse> {
        return webClient.post()
            .uri("/board")
            .bodyValue(boardRequest)
            .retrieve()
            .bodyToMono(BoardResponse::class.java)
    }
}
