package com.chat_project.web.member.controller

import com.chat_project.common.logger
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import com.chat_project.web.member.service.MemberService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
@Tag(name = "Member API", description = "사용자 관련 전체 API")
class MemberController(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository,
    private val modelMapper: ModelMapper,
    private val passwordEncoder: BCryptPasswordEncoder
) {
    val log = logger()

    @Tag(name = "로그인 이전 테스트")
    @Operation(method = "POST", summary = "사용자 회원가입", description = "사용자 회원가입")
    @PostMapping("/signUp")
    fun signUp(memberDTO: MemberDTO) : ResponseEntity<String>
         = ResponseEntity.status(HttpStatus.OK).body(memberService.signUp(memberDTO))


    @Tag(name = "로그인 이전 테스트")
    @Operation(method = "POST", summary = "사용자 로그인", description = "사용자 로그인")
    @PostMapping("/login")
    fun login(email: String, password: String) : ResponseEntity<MemberDTO> =
        ResponseEntity.status(HttpStatus.OK).body(memberService.login(email, password))

    @Tag(name = "로그인 이후 테스트")
    @Operation(method = "GET", summary = "사용자 정보 조회")
    @GetMapping("/info")
    fun info(user: User): ResponseEntity<MemberDTO>
        = memberService.info(user.username)
            .let { return ResponseEntity.status(HttpStatus.OK).body(it) }


    @Tag(name = "로그인 이후 테스트")
    @Operation(method = "PUT", summary = "사용자 정보 수정")
    @PutMapping("/update")
    fun update(memberDTO: MemberDTO): ResponseEntity<String> {
        var result = "";
        result = memberService.update(memberDTO)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @Tag(name = "로그인 이후 테스트")
    @Operation(method = "DELETE", summary = "사용자 정보 삭제")
    @DeleteMapping("/delete")
    fun delete(memberDTO: MemberDTO): ResponseEntity<String> {
        var result = "";
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

}