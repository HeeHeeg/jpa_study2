package com.example.jpa_day2.config.repository;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MemberLoginRepository extends JpaRepository<MemberLogin, Long> {
    Optional<MemberLogin> findFirstByMemberIdAndEndAtAfterOrderByEndAtDesc
            (Long memberId, LocalDateTime now);
}
