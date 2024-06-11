package com.chat_project.redis

import com.chat_project.common.util.RedisUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RedisTest @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val redisBlackListTemplate: RedisTemplate<String, Any>
) {
    @Test
    fun setData() {
        RedisUtil(redisTemplate).setData("dddd", "ee", 3600)
    }

    @Test
    fun getData() {
        val value = RedisUtil(redisTemplate).getData("dddd")
        print(value)
    }

    @Test
    fun deleteData() {
        RedisUtil(redisTemplate).deleteData("dddd")
    }
}