package com.example.jpa_day2.config.domain.entity;

import com.example.jpa_day2.members.domain.entity.Members;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberLogin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Members member; // 어떤 멤버가 로그안 했는지 볼 수 있게끔 멤버를 넣어줘야 한다.
    private LocalDateTime createAt;
    private LocalDateTime endAt;

    public MemberLogin(Members member, LocalDateTime createAt) {
        this.member = member;
        this.createAt = createAt;
        this.endAt = createAt.plusMinutes(10); //10분
    }
}
