package com.springboot.webmvc.config;

import com.springboot.webmvc.aop.TimeTraceAop;
import com.springboot.webmvc.repository.*;
import com.springboot.webmvc.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public TimeTraceAop timeTraceAOP () {
        return new TimeTraceAop();
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
/*
      Using map collection for membership
      OCP (Open-Closed Principle)
 */
//      return new MemoryMemberRepository();
//        return new JdbcMemberRepository(this.dataSource);
        return new JdbcTemplateMemberRepository(this. dataSource);
    }
}
