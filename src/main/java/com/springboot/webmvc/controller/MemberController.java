package com.springboot.webmvc.controller;

import com.springboot.webmvc.domain.Member;
import com.springboot.webmvc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/list")
    public String MembershipList(Model model) {
        List<Member> allMembers = memberService.findAllMembers();
        model.addAttribute("members", allMembers);
        return "members/member_list_page";
    }

    @GetMapping("/members/add")
    public String MembershipAdd() {
        return "members/member_add_page";
    }

    @PostMapping("/members/register")
    public String MembershipRegister(Member member){
        Long uid = memberService.registerMember(member);
        return "redirect:/members/list";
    }
}
