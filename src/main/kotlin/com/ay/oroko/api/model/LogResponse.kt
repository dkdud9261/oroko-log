package com.ay.oroko.api.model

import reactor.core.publisher.Mono

data class LogResponse(
    val month: Int,
    val logs: MutableMap<Int, Int>
) {
    constructor(month: Int) : this(
        month = month,
        logs = sortedMapOf()
    )

    fun put(month:Int): Mono<LogResponse> {
        logs[month] = logs[month]?.plus(1) ?: 1
        return Mono.just(this)
    }
}