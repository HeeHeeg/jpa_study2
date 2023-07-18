package com.example.jpa_day2.config.service;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.members.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberLoginRepository memberLoginRepository;

    // TODO: INSERT
    public void insert(Member member) {
        MemberLogin memberLogin = new MemberLogin(member, LocalDateTime.now());
        memberLoginRepository.save(memberLogin);
    }


    //  login check 하는 것 만들거임. findByMember 로 찾아줘~ 하면 된다.
}
