package com.chat_project.web.member.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/member")
class MemberController {

    @GetMapping("/login")
    fun login() {

    }

}