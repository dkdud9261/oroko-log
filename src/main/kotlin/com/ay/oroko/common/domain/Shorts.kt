package com.ay.oroko.common.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "shorts")
data class Shorts(
    @Id val shortsId : String,
    val content : String,
    val createdAt : LocalDateTime = LocalDateTime.now()
)