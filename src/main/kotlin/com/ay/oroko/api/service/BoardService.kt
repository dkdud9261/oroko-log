package com.ay.oroko.api.service

import com.ay.oroko.api.model.BoardResponse
import com.ay.oroko.common.domain.Board
import com.ay.oroko.api.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {

    fun save(): Mono<Board> = boardRepository.save(Board("1", "testTitle", "/here"))

    fun read(pageable: Pageable): Flux<BoardResponse> = boardRepository.findAll(pageable)
        .flatMapSequential { Mono.just(BoardResponse(it)) }
}