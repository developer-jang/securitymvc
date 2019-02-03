package com.example.securitymvc.controller;

import com.example.securitymvc.domain.Member;
import com.example.securitymvc.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/join")
    public void join() {

    }

    @Transactional
    @PostMapping("join")
    public String joinPost(@ModelAttribute("member") Member member, Model model) {
        String encryptPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptPassword);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "member/joinResult";
    }
}
