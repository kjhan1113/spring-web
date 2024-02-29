package com.springboot.webmvc.service;

import com.springboot.webmvc.domain.Member;
import com.springboot.webmvc.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@jakarta.transaction.Transactional
public class MemberServiceJdbcTest {

    // Autowired from SpringConfig.class
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void JdbcAddMemberTest() {
        Member member = new Member();
        member.setName("Test Member");

        Long uid = memberService.registerMember(member);

        Member foundMember = memberService.findMemberById(uid).orElseThrow(NullPointerException::new);
        Assertions.assertThat(member.getName()).isEqualTo(foundMember.getName());
    }
}
