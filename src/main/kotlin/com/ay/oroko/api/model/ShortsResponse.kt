package com.ay.oroko.api.model

import com.ay.oroko.common.domain.Shorts
import java.time.LocalDateTime

data class ShortsResponse(
    val content : String,
    val createdAt : LocalDateTime
) {
    constructor(shorts: Shorts) : this(
        content = shorts.content,
        createdAt = shorts.createdAt
    )
}