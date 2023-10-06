package com.ay.oroko.api.repository

import com.ay.oroko.common.domain.Shorts
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ShortsRepository : ReactiveMongoRepository<Shorts, String>, CustomShortsRepository

interface CustomShortsRepository {
    fun findAll(pageable: Pageable) : Flux<Shorts>
}

class CustomShortsRepositoryImpl(
    private val mongoTemplate: ReactiveMongoTemplate
) : CustomShortsRepository {
    override fun findAll(pageable: Pageable) : Flux<Shorts> {
        val query = Query()
            .with(Sort.by(Shorts::createdAt.name).descending())
            .with(pageable)
        return mongoTemplate.find(query, Shorts::class.java)
    }
}