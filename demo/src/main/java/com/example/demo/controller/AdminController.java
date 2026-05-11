package com.example.demo.controller;

import com.example.demo.Question;
import com.example.demo.model.Role;
import com.example.demo.model.Users;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CategoryRepository categoryRepository;
    private final QuestionService questionService;

    public AdminController(QuestionService questionService,
                           CategoryRepository categoryRepository) {
        this.questionService = questionService;
        this.categoryRepository = categoryRepository;
    }

    // LIST QUESTIONS
    @GetMapping
    public String adminPage(HttpSession session,
                            Model model) {

        Users user =
                (Users) session.getAttribute("user");

        if(user == null ||
                user.getRole() != Role.ADMIN) {

            return "redirect:/login";
        }

        model.addAttribute(
                "questions",
                questionService.findAll()
        );

        return "admin";
    }

    // ADD QUESTION PAGE
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll()); // 👈 ΝΕΟ
        return "add-question";
    }

    // SAVE QUESTION
    @PostMapping("/add")
    public String addQuestion(@RequestParam String text,
                              @RequestParam String opt1,
                              @RequestParam String opt2,
                              @RequestParam String opt3,
                              @RequestParam String opt4,
                              @RequestParam int correctIndex,
                              @RequestParam Long categoryId) { // 👈 ΝΕΟ

        questionService.save(text, opt1, opt2, opt3, opt4, correctIndex, categoryId);

        return "redirect:/admin";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        questionService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {

        Question question = questionService.findById(id);

        model.addAttribute("question", question);

        model.addAttribute("categories",
                categoryRepository.findAll());

        return "edit-question";
    }


    @PostMapping("/edit")
    public String updateQuestion(@RequestParam Long id,
                                 @RequestParam String text,
                                 @RequestParam Long categoryId,
                                 @RequestParam String opt1,
                                 @RequestParam String opt2,
                                 @RequestParam String opt3,
                                 @RequestParam String opt4,
                                 @RequestParam int correctIndex) {

        questionService.update(
                id,
                text,
                categoryId,
                opt1,
                opt2,
                opt3,
                opt4,
                correctIndex
        );

        return "redirect:/admin";
    }
}