package com.chat_project.exception

enum class CustomExceptionCode(
    val message: String,
) {
    NOT_FOUND_MEMBER("사용자를 찾을 수 없습니다.")
}