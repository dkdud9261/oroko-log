package com.ay.oroko.api.service

import com.ay.oroko.api.model.BoardRequest
import com.ay.oroko.api.model.BoardResponse
import com.ay.oroko.common.domain.Board
import com.ay.oroko.api.repository.BoardRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    fun save(boardRequest: BoardRequest): Mono<BoardResponse> {
        return boardRepository.save(
            Board(
                title = boardRequest.title,
                contentPath = boardRequest.contentPath
            )
        ).flatMap { Mono.just(BoardResponse(it)) }
    }

    fun read(pageable: Pageable): Flux<BoardResponse> {
        return boardRepository.findAll(pageable)
            .flatMapSequential { Mono.just(BoardResponse(it)) }
    }

    fun getDailyBoardLog(date: LocalDate) : Flux<Board> {
        val baseDate: LocalDate = LocalDate.of(date.year, date.month, 1)
        val baseTime: LocalTime = LocalTime.of(0, 0)

        return boardRepository.findAllByCreatedAtBetween(
            LocalDateTime.of(baseDate, baseTime),
            LocalDateTime.of(baseDate.plusMonths(1), baseTime)
        )
    }
}