package com.example.jpa_day2.members.domain.repository;

import com.example.jpa_day2.members.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
