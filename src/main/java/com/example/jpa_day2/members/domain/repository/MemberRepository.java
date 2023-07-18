package com.example.jpa_day2.members.domain.repository;

import com.example.jpa_day2.members.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //있는지 없는지 모를 떄 옵셔널을 쓰면됨.

    //select * from members where email = ? and password = ?; 이렇게 나올거다. 모르겠음 직접 쿼리를 쳐라. 쿼리칠 줄 알아야 한다.
    Optional<Member> findByEmailAndPassword(String email, String password);
}
