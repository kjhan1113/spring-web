package com.springboot.webmvc.config;

import com.springboot.webmvc.repository.MemberRepository;
import com.springboot.webmvc.repository.MemoryMemberRepository;
import com.springboot.webmvc.service.MemberService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
