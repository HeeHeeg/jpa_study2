package com.example.jpa_day2.members.domain.service;

import com.example.jpa_day2.members.domain.repository.MemberRepository;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //여기서 insert는 signup이다.
    public void insert(SignupRequest request) { // 아무것도 반환을 안해주니 그냥 insert로 적어줌.
        memberRepository.save(request.toEntity());
    }

}
