package com.ay.oroko.api.service

import com.ay.oroko.api.model.LogResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate

@Service
class HomeService(
    private val boardService: BoardService,
    private val shortsService: ShortsService
) {

    fun getLog(date: LocalDate): Mono<LogResponse> {
        val logResponse = LogResponse(date.monthValue)

        val boardLogs = boardService.getDailyBoardLog(date)
            .flatMap {
                logResponse.put(it.createdAt.dayOfMonth)
            }

        val shortsLogs = shortsService.getDailyShortsLog(date)
            .flatMap {
                logResponse.put(it.createdAt.dayOfMonth)
            }

        return Flux.concat(boardLogs, shortsLogs)
            .last()
    }
}