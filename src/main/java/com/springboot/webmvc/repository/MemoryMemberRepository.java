package com.springboot.webmvc.repository;

import com.springboot.webmvc.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> memberRepository = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member addMember(Member member) {
        member.setId(++sequence);
        memberRepository.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberRepository.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return memberRepository.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAllMembers() {
        return new ArrayList<>(memberRepository.values());
    }

    @Override
    public void clearData() {
        memberRepository.clear();
    }
}
