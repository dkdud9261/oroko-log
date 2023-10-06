package com.ay.oroko

import com.ay.oroko.api.repository.BoardRepository
import com.ay.oroko.common.domain.Board
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class InitData(
    private val boardRepository: BoardRepository
) {

    @PostConstruct
    fun initData() {
//        boardRepository.save(Board(title = "title1", contentPath = "path1")).subscribe()
//        boardRepository.save(Board(title = "title2", contentPath = "path2")).subscribe()
//        boardRepository.save(Board(title = "title3", contentPath = "path3")).subscribe()
//        boardRepository.save(Board(title = "title4", contentPath = "path4")).subscribe()
//        boardRepository.save(Board(title = "title5", contentPath = "path5")).subscribe()
    }
}