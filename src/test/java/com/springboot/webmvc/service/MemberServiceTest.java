package com.springboot.webmvc.service;

import com.springboot.webmvc.domain.Member;
import com.springboot.webmvc.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearData();
    }

    @Test
    void registerMember() {
        // Given
        Member member = new Member();
        member.setName("member 1");

        // When
        Long registeredID = memberService.registerMember(member);

        // Then
        Member result = memberService.findMemberById(registeredID).orElseThrow(NullPointerException::new);
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    void checkingDuplicatedMember() {

        // given
        Member member1 = new Member();
        member1.setName("member 1");

        Member member2 = new Member();
        member2.setName("member 1");

        // when
        memberService.registerMember(member1);

        // Then
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> memberService.registerMember(member2));
        assertThat(ex.getMessage()).isEqualTo("This member already exists");
    }
}