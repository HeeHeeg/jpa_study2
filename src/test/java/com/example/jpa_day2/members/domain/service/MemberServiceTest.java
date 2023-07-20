package com.example.jpa_day2.members.domain.service;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.members.domain.repository.MemberRepository;
import com.example.jpa_day2.members.domain.request.LoginRequest;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import com.example.jpa_day2.members.domain.response.LoginResponse;
//import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired // 빈에서 가져오려고 사용.
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberLoginRepository memberLoginRepository;
    @BeforeEach //모든것 하기 전에 한번씩 돌아가겠다.
    void init() {
        String email = "1111";
        String password = "1234";
        LoginRequest loginRequest = new LoginRequest(email, password);
        Members member = new Members(null, email, password, "name", 10, null, null);
        memberRepository.save(member);
    }

    @AfterEach
    void clear() {
        memberRepository.deleteAll();
        memberLoginRepository.deleteAll();
    }

    @Test
    void insert() {
        //given - 테스트 하려고 데이터를 줌
        String email = "ssss";
        String password = "1234";
        String name = "uuuu";
        Integer age = 10;
        SignupRequest signupRequest = new SignupRequest(email, password, name, age);

        // when - 넣어주기
        memberService.insert(signupRequest);

        //then - 잘 들어갔나 확인
        List<Members> all = memberRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getEmail()).isEqualTo(email);
    }

    private List<Members> all() {
        return memberRepository.findAll();
    }

    @Test
     // 통채로 하나의 엔티티메니저르르 사용하기 위해서 쓴 것.
    public void 기본로그인_멤버로그인_인서트_체크() {
        //given
        String email = "1111";
        String password = "1234";
        LoginRequest loginRequest = new LoginRequest(email, password);
        Members member = new Members(null, email, password, "name", 10, null, null);
        memberRepository.save(member);

        //when
        LoginResponse login = memberService.login(loginRequest);

        //then
        assertThat(login.age()).isEqualTo(10);
        assertThat(login.name()).isEqualTo("name");
        assertThat(login.id()).isNotNull();
        List<MemberLogin> all = memberLoginRepository.findAll();
        assertThat(all).hasSize(1);
        assertThat(all.get(0).getMember()).isEqualTo(member);
        assertThat(all.get(0).getCreateAt()).isBefore(LocalDateTime.now());
        assertThat(all.get(0).getEndAt()).isAfter(LocalDateTime.now());

    }

    @Test
    void 로그인시_없는_유저() {
        //given
        String email = "1111";
        String password = "1234";
        LoginRequest loginRequest = new LoginRequest(email, password);
        Members member = new Members(null, email + "333", password, "name", 10, null, null);
        memberRepository.save(member);

        //when
        RuntimeException runtimeException = Assertions.assertThrows(
                RuntimeException.class,
                () -> memberService.login(loginRequest));
        assertThat(runtimeException).hasMessage("없는 유저");


   /*     //when & then - 여기서 에러가 나면 에러 메세지 '없는 유저'가 맞는지
        assertThatThrownBy(()->memberService.login(loginRequest)).hasMessage("없는 유저");*/
    }


}