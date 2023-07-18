package com.example.jpa_day2.config.service;

import com.example.jpa_day2.config.repository.MemberLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberLoginRepository memberLoginRepository;
    // TODO: INSERT
    //  login check 하는 것 만들거임. findByMember 로 찾아줘~ 하면 된다.
}
