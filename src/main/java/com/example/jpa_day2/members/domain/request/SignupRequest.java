package com.example.jpa_day2.members.domain.request;

import com.example.jpa_day2.members.domain.entity.Members;

public record SignupRequest(String email
        , String password
        , String name
        , Integer age) {

    //엔티티로 바꿔주는 메서드 만들기.
    public Members toEntity() {
        return Members.builder()
                .email(email)
                .password(password)
                .age(age)
                .name(name)
                .build();
    }
}
