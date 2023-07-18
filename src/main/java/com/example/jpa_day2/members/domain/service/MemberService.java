package com.example.jpa_day2.members.domain.service;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.config.repository.MemberLoginRepository;
import com.example.jpa_day2.config.service.MemberLoginService;
import com.example.jpa_day2.members.domain.entity.Member;
import com.example.jpa_day2.members.domain.repository.MemberRepository;
import com.example.jpa_day2.members.domain.request.LoginRequest;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import com.example.jpa_day2.members.domain.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberLoginService memberLoginService;

    //여기서 insert는 signup이다.
    public void insert(SignupRequest request) { // 아무것도 반환을 안해주니 그냥 insert로 적어줌.
        memberRepository.save(request.toEntity());
    }

    public LoginResponse login(LoginRequest request) {
        Optional<Member> byEmailAndPassword =
                memberRepository.findByEmailAndPassword(request.email(), request.password());
        request.email(); // 1.email을 찾아야 하는데 그냥 찾을 수 없다. -> 리퀘스트에서 findByEmailAndPassword 만들어준다.
        //옵셔널은 그 자체가 멤버가 아니라 한번 꺼내줘야 한다.
        //있으면 꺼내고 없으면 에러를 던져주라고 설정.
        Member member =
                byEmailAndPassword.orElseThrow(() -> new RuntimeException("없는 유저"));// -> 이제 멤버가 꺼내진거다. 여기까지 하면 로그인이 되는 것.
//        new MemberLogin(member, LocalDateTime.now()); 여기다 하지 말고 공유 폴더로 만들어서 사용 ->cofig -memberLogin을 따로 만듬.
        memberLoginService.insert(member);
        //로그인 하고 부터는 password가 더이상 필요 없다. 확실하게 빼줄거는 거르고 빼줘야 한다. 이제  response를 만들어보자.
        return new LoginResponse(member.getId(), member.getName(), member.getAge());

    }
}
