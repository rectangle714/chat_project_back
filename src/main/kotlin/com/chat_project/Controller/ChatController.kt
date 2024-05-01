package com.chat_project.Controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class ChatController {

    @GetMapping("/main")
    fun main(model: Model): String {
        model["title"] = "testTitle"
        return "index"
    }

}