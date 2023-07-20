package com.example.jpa_day2.config.domain.dto;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import com.example.jpa_day2.members.domain.entity.Members;
import lombok.Getter;

import java.lang.reflect.Member;

@Getter
public class MemberDto {
        private Long id;
        private String email;
        private String name;
        private Integer age;

    public MemberDto(Members member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.age = member.getAge();
    }
}
