package com.ay.oroko.api.repository

import com.ay.oroko.common.domain.Board
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import java.time.LocalDateTime

interface BoardRepository : ReactiveMongoRepository<Board, String>, CustomBoardRepository {
    fun findAllByCreatedAtBetween(from: LocalDateTime, to: LocalDateTime): Flux<Board>
}

interface CustomBoardRepository {
    fun findAll(pageable: Pageable) : Flux<Board>
}

class CustomBoardRepositoryImpl(
    private val mongoTemplate: ReactiveMongoTemplate
) : CustomBoardRepository {
    override fun findAll(pageable: Pageable) : Flux<Board> {
        val query = Query()
            .with(Sort.by(Board::createdAt.name).descending())
            .with(pageable)
        return mongoTemplate.find(query, Board::class.java)
    }
}