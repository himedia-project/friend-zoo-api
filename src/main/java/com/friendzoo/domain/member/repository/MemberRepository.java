package com.friendzoo.domain.member.repository;

import com.friendzoo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select u from Member u where u.email = :email") // leftjoin + fetch join (한번에 조회 ) Pagination OOM ->
    Optional<Member> findByEmail(@Param("email") String email);
}
