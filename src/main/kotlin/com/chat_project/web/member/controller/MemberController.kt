package com.chat_project.web.member.controller

import com.chat_project.web.config.ModelMapperConfig
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
@Tag(name = "Member API", description = "Member API")
class MemberController {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var modelMapper: ModelMapper

    @PostMapping("/login")
    fun login(email:String) : ResponseEntity<MemberDTO> {
        val member = memberRepository.findByEmail(email)
        val memberDTO = modelMapper.map(member, MemberDTO::class.java)
        return ResponseEntity.ok(memberDTO)
    }

}