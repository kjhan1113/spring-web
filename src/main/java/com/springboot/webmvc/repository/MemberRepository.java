package com.springboot.webmvc.repository;

import com.springboot.webmvc.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member addMember(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAllMembers();

    void clearData();
}
