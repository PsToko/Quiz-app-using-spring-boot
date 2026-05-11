package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Users;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public List<Users> getUsers() {
        return userRepository.findAll();
    }
}