package com.ay.oroko.common.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "board")
data class Board(
    @Id val boardId: String,
    val title: String,
    val contentPath: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)