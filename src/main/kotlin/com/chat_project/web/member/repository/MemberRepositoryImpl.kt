package com.chat_project.web.member.repository

import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.entity.QMember.member
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory

class MemberRepositoryImpl(
    private val query: JPAQueryFactory
) : MemberRepositoryCustom {

    override fun findByEmail(email: String): Member =
        query.select(
            Projections.bean(
                Member::class.java,
                member.email, member.password, member.nickname, member.role, member.registerDate, member.updateDate
            )
        )
        .from(member)
        .where(member.email.eq(email))
        .fetchOne()!!

}