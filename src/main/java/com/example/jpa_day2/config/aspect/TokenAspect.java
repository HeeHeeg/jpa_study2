package com.example.jpa_day2.config.aspect;

import com.example.jpa_day2.config.auth.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component //빈에 한번 올려서 빈에 올린걸 쓰려고 붙여주는 것.
public class TokenAspect {
    @Autowired
    private AuthService authService;

    @Before("@annotation(TokenRequired)")
    public void checkToken() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder
                        .currentRequestAttributes(); //현재 요청을 가져오겠다.

        String authorization = requestAttributes.getRequest().getHeader("authorization");
    }
}
