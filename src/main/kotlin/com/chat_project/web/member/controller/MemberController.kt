package com.chat_project.web.member.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/member")
@Tag(name = "Member API", description = "Member API 입니다.")
class MemberController {

    @GetMapping("/login")
    fun login() {

    }

}