package com.ay.oroko.api.controller

import com.ay.oroko.api.model.LogResponse
import com.ay.oroko.api.service.HomeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDate

@RestController
@RequestMapping("/home")
class HomeController(
    private val homeService: HomeService
) {
    @GetMapping("/log")
    fun getLogs(
        @RequestParam("year", required = false) year: Int?,
        @RequestParam("month", required = false) month: Int?
    ): Mono<LogResponse> {
        return homeService.getLog(LocalDate.of(
            year ?: LocalDate.now().year,
            month ?: LocalDate.now().monthValue,
            1
        ))
    }
}