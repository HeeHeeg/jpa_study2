package com.example.jpa_day2.members.domain.response;

import org.antlr.v4.runtime.Token;

public record LoginResponse(
        Long id, String name, Integer age, String Token) { // 멤버의 고유값만 넣어줌. 로그인 떄 뺴고는 쓸일이 없음. 서비스에서 직접 넣어주자.
}
