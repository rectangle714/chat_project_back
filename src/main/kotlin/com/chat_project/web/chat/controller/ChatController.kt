package com.chat_project.web.chat.controller

import com.chat_project.web.chat.dto.ChatDTO
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController {

    @ResponseBody
    @PostMapping("/chat")
    fun chat(chat: ChatDTO) : ChatDTO {

        return chat;
    }
}