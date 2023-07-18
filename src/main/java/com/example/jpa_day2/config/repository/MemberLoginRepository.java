package com.example.jpa_day2.config.repository;

import com.example.jpa_day2.config.domain.entity.MemberLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLoginRepository extends JpaRepository<MemberLogin, Long> {
}
