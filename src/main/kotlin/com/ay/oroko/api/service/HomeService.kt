package com.ay.oroko.api.service

import com.ay.oroko.api.model.LogResponse
import com.ay.oroko.common.domain.Board
import com.ay.oroko.common.domain.Shorts
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
        val boardLog: Flux<Board> = boardService.getDailyBoardLog(date)
        val shortsLog: Flux<Shorts> = shortsService.getDailyShortsLog(date)

        return Mono.just(with(LogResponse(date.monthValue)) {
            boardLog.subscribe {
                this.put(it.createdAt.dayOfMonth)
            }
            shortsLog.subscribe {
                this.put(it.createdAt.dayOfMonth)
            }
            this
        })
    }
}