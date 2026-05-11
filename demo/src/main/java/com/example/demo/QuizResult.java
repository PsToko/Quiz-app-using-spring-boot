package com.example.demo;

import com.example.demo.model.Users;
import jakarta.persistence.*;

@Entity
public class QuizResult {
    @Id @GeneratedValue
    Long id;

    int score;

    @ManyToOne
    Users user;

    @ManyToOne
    Category category;
}