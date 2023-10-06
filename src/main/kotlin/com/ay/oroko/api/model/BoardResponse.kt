package com.ay.oroko.api.model

import com.ay.oroko.common.domain.Board
import java.time.LocalDateTime

data class BoardResponse(
    val title: String,
    val contentPath: String,
    val createdAt: LocalDateTime
) {
    constructor(board: Board) : this(
        title = board.title,
        contentPath = board.contentPath,
        createdAt = board.createdAt
    )
}