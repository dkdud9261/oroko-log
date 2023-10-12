package com.ay.oroko.api.service

import com.ay.oroko.api.model.ShortsResponse
import com.ay.oroko.api.repository.ShortsRepository
import com.ay.oroko.common.domain.Shorts
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class ShortsService(
    private val shortsRepository: ShortsRepository
) {
    fun write(content: String): Mono<ShortsResponse> {
        return shortsRepository.save(Shorts(content = content))
            .flatMap { Mono.just(ShortsResponse(it)) }
    }

    fun read(pageable: Pageable): Flux<ShortsResponse> {
        return shortsRepository.findAll(pageable)
            .flatMapSequential { Mono.just(ShortsResponse(it)) }
    }

    fun getDailyBoardLog(date: LocalDate) : Flux<Shorts> {
        val baseDate: LocalDate = LocalDate.of(date.year, date.month, 1)
        val baseTime: LocalTime = LocalTime.of(0, 0)

        return shortsRepository.findAllByCreatedAtBetween(
            LocalDateTime.of(baseDate, baseTime),
            LocalDateTime.of(baseDate.plusMonths(1), baseTime)
        )
    }
}