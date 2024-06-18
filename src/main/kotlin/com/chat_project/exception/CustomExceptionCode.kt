package com.chat_project.exception

import org.springframework.http.HttpStatus

enum class CustomExceptionCode(
    val status: String,
    val message: String,
) {
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST.toString(),"사용자를 찾을 수 없습니다.")
}