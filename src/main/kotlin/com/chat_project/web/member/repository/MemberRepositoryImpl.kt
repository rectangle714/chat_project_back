package com.chat_project.web.member.repository

import com.chat_project.web.member.entity.Member
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource

@Repository
class MemberRepositoryImpl(
    @Resource(name= "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(Member::class.java) {



}