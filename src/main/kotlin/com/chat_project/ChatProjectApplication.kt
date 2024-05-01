package com.chat_project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatProjectApplication

fun main(args: Array<String>) {
    runApplication<ChatProjectApplication>(*args)
}
