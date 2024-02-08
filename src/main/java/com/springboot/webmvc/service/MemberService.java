package com.springboot.webmvc.service;

import com.springboot.webmvc.domain.Member;
import com.springboot.webmvc.repository.MemberRepository;
import com.springboot.webmvc.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long registerMember(Member member) {
        checkingDuplicatedMember(member);
        memberRepository.addMember(member);
        return member.getId();
    }

    private void checkingDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(mmm -> {
            throw new IllegalStateException("This member already exists");
        });
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAllMembers();
    }

    public Optional<Member> findMemberById(Long memberID){
        return memberRepository.findById(memberID);
    }
}