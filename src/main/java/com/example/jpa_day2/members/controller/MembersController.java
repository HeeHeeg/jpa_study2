package com.example.jpa_day2.members.controller;

import com.example.jpa_day2.config.aspect.TokenRequired;
import com.example.jpa_day2.members.domain.request.LoginRequest;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import com.example.jpa_day2.members.domain.response.LoginResponse;
import com.example.jpa_day2.members.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MembersController {
    private final MemberService service;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@RequestBody SignupRequest signupRequest) {
        service.insert(signupRequest);
    }

    @GetMapping
    @TokenRequired // 애노테이션 만들어 달기.
    public void getAll(@RequestParam(required = false, defaultValue = "0", name = "page")
                       Integer page,
                       @RequestParam(required = false, defaultValue = "3", name = "size")
                       Integer size
    ) {
        service.findAll(PageRequest.of(page, size));

    }

    @GetMapping("test")
    @TokenRequired // 토큰이 무조건 필요하다고 달아주는 것.
    public Map<String, Object> test(
            @RequestHeader("Authorization") String token) {
        System.out.println("ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");
        return service.getTokenToData(token.replace("Bearer", ""));
    }

}
