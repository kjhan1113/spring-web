package com.springboot.webmvc.repository;

import com.springboot.webmvc.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member addMember(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
         return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.ofNullable(em.find(Member.class, name));
    }

    @Override
    public List<Member> findAllMembers() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public void clearData() {

    }
}
