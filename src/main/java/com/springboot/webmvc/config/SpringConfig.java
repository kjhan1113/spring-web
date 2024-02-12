package com.springboot.webmvc.config;

import com.springboot.webmvc.repository.JdbcMemberRepository;
import com.springboot.webmvc.repository.MemberRepository;
import com.springboot.webmvc.repository.MemoryMemberRepository;
import com.springboot.webmvc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//      Using map collection for membership
//      return new MemoryMemberRepository();
        return new JdbcMemberRepository(this.dataSource);
    }
}
