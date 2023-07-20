package com.example.jpa_day2.config.service;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.members.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberLoginServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberLoginService memberLoginService;
    @Autowired
    MemberLoginRepository memberLoginRepository;
    String email = "1111";
    String password = "1234";
    Members member;
    @BeforeEach
        //모든것 하기 전에 한번씩 돌아가겠다.
    void init() {
        Members member = new Members(null, email, password, "name", 10, null, null);
        this.member = memberRepository.save(member);
        /*LoginRequest loginRequest = new LoginRequest(email, password);
        memberRepository.save(member);
        Member save = memberRepository.save(member);*/
        MemberLogin entity = new MemberLogin(member, LocalDateTime.now());
        memberLoginRepository.save(entity);
    }
    @AfterEach
    void clear() {
        memberLoginRepository.deleteAll();
        memberRepository.deleteAll();
    }
    @Test
    void findByMember() {
        //given
        Long memberId = member.getId();

        //when
        Members byMember = memberLoginService.findByMember(memberId);

        //then
        Assertions.assertThat(byMember).isEqualTo(this.member);
        Assertions.assertThat(byMember.getId()).isEqualTo(memberId);
        Assertions.assertThat(byMember.getEmail()).isEqualTo(email);
        Assertions.assertThat(byMember.getPassword()).isEqualTo(password);
    }

    @Test
    void 가장최근것찾기() {
        //given
        Members member = memberRepository.findAll().get(0);
        Long memberId = member.getId();
        MemberLogin entity = new MemberLogin(member, LocalDateTime.now());
        MemberLogin save = memberLoginRepository.save(entity);

        //when
        Members byMember = memberLoginService.findByMember(memberId);

        //then
        Assertions.assertThat(save.getMember()).isEqualTo(byMember);
    }

    @Test
    void 로그인상태가아닌경우() {
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class
                , ()-> memberLoginService.findByMember(100000L));
        Assertions.assertThat(runtimeException)
                .hasMessage("로그인 상태가 아닙니다.");

    }


    @Test
    void insert() {
    }

}