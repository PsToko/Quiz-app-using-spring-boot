package com.example.demo.controller;

import com.example.demo.Question;
import com.example.demo.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private final QuestionService questionService;

    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String quizPage(Model model) {

        model.addAttribute(
                "questions",
                questionService.getQuiz()
        );

        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(HttpServletRequest request,
                             Model model) {

        int score =
                questionService.calculateScore(request);

        model.addAttribute("score", score);

        return "result";
    }
}
