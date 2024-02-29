package com.springboot.webmvc.service;

import com.springboot.webmvc.domain.Member;
import com.springboot.webmvc.repository.MemberRepository;
import com.springboot.webmvc.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long registerMember(Member member) {
//        checkingDuplicatedMember(member);
        Member result = memberRepository.addMember(member);
        return result.getId();
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