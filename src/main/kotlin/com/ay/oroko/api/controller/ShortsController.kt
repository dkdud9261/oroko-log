package com.ay.oroko.api.controller

import com.ay.oroko.api.model.ShortsResponse
import com.ay.oroko.api.service.ShortsService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/shorts")
class ShortsController(
    private val shortsService: ShortsService
) {

    @PostMapping fun write(@RequestBody(required = true) content: String) = shortsService.write(content)

    @GetMapping("/list")
    fun read(
        @RequestParam("page", defaultValue = "0", required = false) page: Int,
        @RequestParam("size", defaultValue = "20", required = false) size: Int
    ) : Flux<ShortsResponse> = shortsService.read(PageRequest.of(page, size))
}