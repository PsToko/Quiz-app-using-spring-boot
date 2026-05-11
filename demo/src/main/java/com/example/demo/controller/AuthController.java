package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // LOGIN POST
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        Users user = authService.login(username, password);

        if(user != null) {

            session.setAttribute("user", user);

            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin";
            } else {
                return "redirect:/quiz";
            }
        }

        return "redirect:/login?error=true";
    }


    // REGISTER POST
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        authService.register(username, password);
        return "redirect:/login";
    }
}