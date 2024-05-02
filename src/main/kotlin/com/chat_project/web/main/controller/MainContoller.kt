package com.chat_project.web.main.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main")
class MainContoller {

    @GetMapping("/main")
    fun main(model: Model): String {
        model["title"] = "testTitle"
        return "index"
    }

}