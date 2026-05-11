package com.example.demo.service;


import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String username, String password) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public void createAdmin() {
        Users admin = new Users();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }

    public Users login(String username, String password) {
        Optional<Users> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }
}
