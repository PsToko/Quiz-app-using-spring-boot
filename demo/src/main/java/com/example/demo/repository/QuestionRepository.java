package com.example.demo.repository;

import com.example.demo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT TOP 10 * FROM question ORDER BY NEWID()",
            nativeQuery = true)
    List<Question> getRandomQuiz();
}