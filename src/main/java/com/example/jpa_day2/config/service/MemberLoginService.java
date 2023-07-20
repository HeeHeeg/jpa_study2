package com.example.jpa_day2.config.service;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.members.domain.entity.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberLoginRepository memberLoginRepository;

    // TODO: INSERT
    public void insert(Members member) {
        MemberLogin memberLogin = new MemberLogin(member, LocalDateTime.now());
        memberLoginRepository.save(memberLogin);
    }


    //  login check 하는 것 만들거임. findByMember 로 찾아줘~ 하면 된다.
    public Members findByMember(Long memberId) { // findByMember가 아니라 login check가 되야함.
        //1. test member가 빠져나오냐(memberId랑 같은)
        //2. 가장 최근것 빠져나오냐?
        //3. 없으면 에러 발생.

        Optional<MemberLogin> byMemberId = memberLoginRepository.findFirstByMemberIdAndEndAtAfterOrderByEndAtDesc(memberId, LocalDateTime.now());
        MemberLogin memberLogin = byMemberId
                .orElseThrow(() -> new RuntimeException("로그인 상태가 아닙니다."));
        if (memberLogin.getEndAt().isBefore(LocalDateTime.now()))
            throw new RuntimeException("로그인 상태가 아닙니다.");
        Members member = memberLogin.getMember();
        return member;


    }

}
