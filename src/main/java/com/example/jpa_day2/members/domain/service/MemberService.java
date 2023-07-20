package com.example.jpa_day2.members.domain.service;

import com.example.jpa_day2.config.auth.AuthService;
import com.example.jpa_day2.config.exception.LoginFailException;
import com.example.jpa_day2.config.service.MemberLoginService;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.members.domain.repository.MemberRepository;
import com.example.jpa_day2.members.domain.request.LoginRequest;
import com.example.jpa_day2.members.domain.request.SignupRequest;
import com.example.jpa_day2.members.domain.response.LoginResponse;
import com.example.jpa_day2.members.domain.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberLoginService memberLoginService;
    private final AuthService authService;

    //여기서 insert는 signup이다.
    public void insert(SignupRequest request) { // 아무것도 반환을 안해주니 그냥 insert로 적어줌.
        Optional<Members> byEmail = memberRepository.findByEmail(request.email());
        if (byEmail.isPresent()) throw new RuntimeException("있는 email");
        memberRepository.save(request.toEntity());
    }

    public LoginResponse login(LoginRequest request) {
        Optional<Members> byEmailAndPassword =
                memberRepository.findByEmailAndPassword(request.email(), request.password());
        request.email(); // 1.email을 찾아야 하는데 그냥 찾을 수 없다. -> 리퀘스트에서 findByEmailAndPassword 만들어준다.
        //옵셔널은 그 자체가 멤버가 아니라 한번 꺼내줘야 한다.
        //있으면 꺼내고 없으면 에러를 던져주라고 설정.
        Members member =
                byEmailAndPassword.orElseThrow(() -> new LoginFailException("없는 유저"));// -> 이제 멤버가 꺼내진거다. 여기까지 하면 로그인이 되는 것.
//        new MemberLogin(member, LocalDateTime.now()); 여기다 하지 말고 공유 폴더로 만들어서 사용 ->cofig -memberLogin을 따로 만듬.
        memberLoginService.insert(member);
        String token = authService.makeToken(member);
        //로그인 하고 부터는 password가 더이상 필요 없다. 확실하게 빼줄거는 거르고 빼줘야 한다. 이제  response를 만들어보자.
        return new LoginResponse(member.getId(), member.getName(), member.getAge(), token);
    }

    public Page<MemberResponse> findAll(PageRequest request) {
         Page<Members> allBy = memberRepository.findAllFetch(request);
         return allBy.map(MemberResponse::new);
    }

    public Map<String, Object> getTokenToData(String token) {
        return authService.getClaims(token);
    }

}
