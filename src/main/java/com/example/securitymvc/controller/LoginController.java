package com.example.securitymvc.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class LoginController {

    @GetMapping("/login")
    public void login() {
        log.info("/login");
    }

    @GetMapping("/logout")
    public void logout() {

    }
}
