package com.ay.oroko.api.controller

import com.ay.oroko.api.model.BoardResponse
import com.ay.oroko.common.domain.Board
import com.ay.oroko.api.service.BoardService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {

    @PostMapping
    fun createBoard(): Mono<Board> = boardService.save()

    @GetMapping("/list")
    fun findBoard(
        @RequestParam(value = "page", defaultValue = "0", required = false) page: Int,
        @RequestParam(value = "size", defaultValue = "10", required = false) size: Int
    ): Flux<BoardResponse> = boardService.read(PageRequest.of(page, size))
}