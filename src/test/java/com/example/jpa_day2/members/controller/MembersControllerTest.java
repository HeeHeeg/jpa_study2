package com.example.jpa_day2.members.controller;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.exception.DuplicationException;
import com.example.jpa_day2.config.exception.LoginFailException;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.members.domain.repository.MemberRepository;
import com.example.jpa_day2.members.domain.request.LoginRequest;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import com.example.jpa_day2.members.domain.response.LoginResponse;
import com.example.jpa_day2.members.domain.service.MemberService;
import com.example.jpa_day2.todos.domain.entity.Todo;
import com.example.jpa_day2.todos.repository.TodoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MembersControllerTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    MemberLoginRepository memberLoginRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    /*@MockBean
    MemberService memberService;*/

    @Test
    void 로그인_성공() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest(email, password);
       /* Mockito.when(memberService.login(ArgumentMatchers.any(LoginRequest.class)))
                .thenReturn(new LoginResponse(1l, "name", 12));*/

        //when &then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/members/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty()) // $ -> 바디 표시?
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.age").value(12));
    }

    @Test
    void 로그인_실패() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest(email + "111", password);

      /*  Mockito.when(memberService.login(ArgumentMatchers.any(LoginRequest.class)))
                .thenThrow(LoginFailException.class);*/
//                .thenReturn(new LoginResponse(1l, "name", 12));

        //when &then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest))
                ).andExpect(status().isBadRequest());

    }

    /*  @Test
      void 중복_이메일() throws Exception{
          //given
          LoginRequest loginRequest = new LoginRequest(email, password);
          Mockito.when(memberService.login(ArgumentMatchers.any(LoginRequest.class)))
                  .thenReturn(new LoginResponse(1l, "name", 12));

          //when &then
          mockMvc.perform(
                          MockMvcRequestBuilders.post("/api/v1/members/login")
                                  .contentType(MediaType.APPLICATION_JSON)
                                  .content(objectMapper.writeValueAsString(loginRequest))
                  ).andExpect(status().isBadRequest())
                  .andExpect(jsonPath("$.id").isNotEmpty()) // $ -> 바디 표시?
                  .andExpect(jsonPath("$.name").value("name"))
                  .andExpect(jsonPath("$.age").value(12));

      }*/

    @Test
    void 가입_성공() throws Exception {
        SignupRequest signupRequest =
                new SignupRequest(email + "111", password, "name", 10);
       /* Mockito.doNothing()
                .when(memberService)
                .insert(ArgumentMatchers.any(SignupRequest.class));*/
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/members/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signupRequest))
                )
                .andExpect(status().isCreated());
    }


    @Test
    void 가입_실패() throws Exception {
        SignupRequest signupRequest =
                new SignupRequest(email + "111", password, "name", 10);
      /*  Mockito.doThrow(new DuplicationException("에러"))
                .when(memberService)
                .insert(ArgumentMatchers.any(SignupRequest.class));*/
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/members/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequest))
        )
                .andExpect(status().isBadRequest());
    }







    String email = "1111";
    String password = "1234";
    Members member;

    @BeforeEach
        //모든 메소드 돌기 전에 한번씩 돌아가겠다.
    void init() {
        Members member = new Members(null, email, password, "name", 10, null, null);
        this.member = memberRepository.save(member);
        MemberLogin entity = new MemberLogin(member, LocalDateTime.now());
        memberLoginRepository.save(entity);
        todoRepository.save(new Todo(null, "t", "t", false, 0, this.member));
        todoRepository.save(new Todo(null, "t2", "t2", false, 0, this.member));

    }

    @AfterEach
    void clean() {
        todoRepository.deleteAll();
        memberLoginRepository.deleteAll();
        memberRepository.deleteAll();
    }

}