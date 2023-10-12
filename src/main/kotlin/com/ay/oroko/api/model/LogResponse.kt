package com.ay.oroko.api.model

data class LogResponse(
    val month: Int,
    val logs: HashMap<Int, Int>
) {
    constructor(month: Int) : this(
        month = month,
        logs = HashMap()
    )

    fun put(month:Int) {
        logs.replace(month, logs[month]?.plus(1) ?: 1)
    }
}