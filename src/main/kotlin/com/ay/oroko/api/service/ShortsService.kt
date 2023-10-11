package com.ay.oroko.api.service

import com.ay.oroko.api.model.ShortsResponse
import com.ay.oroko.api.repository.ShortsRepository
import com.ay.oroko.common.domain.Shorts
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

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
}