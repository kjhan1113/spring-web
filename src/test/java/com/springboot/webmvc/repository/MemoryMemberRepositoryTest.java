package com.springboot.webmvc.repository;

import com.springboot.webmvc.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void clearData() {
        repository.clearData();
    }

    @Test
    void addMember() {
        Member member = new Member();
        member.setName("member 1");

        Member addedMember = repository.addMember(member);
        Member result = repository.findById(addedMember.getId()).orElseThrow(NullPointerException::new);

        assertThat(member).isEqualTo(result);
    }

    @Test
    void findById() {
        Member member = new Member();
        member.setName("member 1");
        repository.addMember(member);

        Member result = repository.findById(member.getId()).orElseThrow(NullPointerException::new);
        assertThat(result.getId()).isEqualTo(member.getId());
    }

    @Test
    void findByName() {
        Member member = new Member();
        member.setName("member 1");
        repository.addMember(member);

        Member result = repository.findByName("member 1").orElseThrow(NullPointerException::new);
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    void findAllMembers() {
        Member member1 = new Member();
        member1.setName("member 1");
        repository.addMember(member1);

        Member member2 = new Member();
        member2.setName("member 2");
        repository.addMember(member2);

        List<Member> result = repository.findAllMembers();
        assertThat(result.size()).isEqualTo(2);
    }
}