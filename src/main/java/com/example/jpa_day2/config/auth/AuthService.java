package com.example.jpa_day2.config.auth;

import com.example.jpa_day2.members.domain.entity.Members;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Service
public class AuthService {
    // 토큰 만들기
    public String makeToken(Members members) {
        String secretKey = "secret-dsdfsd-f-sd-f-ds-f-ds-fsdfd-fs-df-d-f-sd-f-dsdsfsdf-ds-dsakey";
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs256.getJcaName());
        String compact = Jwts.builder()
                .claim("memberId", members.getId())
                .claim("name", members.getName())
                .claim("age", members.getAge())
                .setExpiration(new Date(System.currentTimeMillis() + 120_000)) //setExpiration 토큰 만료시간. jjwt io에서 iat부분이다./ 120_000->2분 유지하겠다.
                .signWith(key)
                .compact();//compact를 하면 이게 string으로 튀어나온다.
        return compact;

    }

    //토큰 검증
    public Map<String, Object> getClaims(String token) {
        String secretKey = "secret-dsdfsd-f-sd-f-ds-f-ds-fsdfd-fs-df-d-f-sd-f-dsdsfsdf-ds-dsakey";
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), hs256.getJcaName());
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parse(token)
                .getBody();

    }


}
