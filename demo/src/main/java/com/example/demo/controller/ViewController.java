package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.example.demo.service.QuestionService;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final QuestionService questionService;

    public ViewController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }


}