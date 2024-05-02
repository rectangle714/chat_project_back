package com.chat_project.web.member.entity

import jakarta.persistence.*

@Entity
class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", )
    var id : Long = 0L
    var email : String = ""
    var name : String = ""
    var nickname : String = ""
}