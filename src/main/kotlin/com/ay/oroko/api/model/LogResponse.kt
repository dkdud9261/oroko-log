package com.ay.oroko.api.model

data class LogResponse(
    val month: Int,
    val logs: MutableMap<Int, Int>
) {
    constructor(month: Int) : this(
        month = month,
        logs = hashMapOf()
    )

    fun put(month:Int) {
        logs[month] = logs[month]?.plus(1) ?: 1
    }
}