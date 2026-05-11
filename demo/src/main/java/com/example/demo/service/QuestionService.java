package com.example.demo.service;

import com.example.demo.Category;
import com.example.demo.Options;
import com.example.demo.Question;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OptionRepository;
import com.example.demo.repository.QuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final CategoryRepository categoryRepository;

    public List<Question> getQuiz() {
        return questionRepository.getRandomQuiz();
    }

    public QuestionService(QuestionRepository questionRepository,
                           OptionRepository optionRepository,
                           CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public void save(String text, String o1, String o2, String o3, String o4,
                     int correctIndex, Long categoryId) {

        Question q = new Question();
        q.setText(text);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        q.setCategory(category);

        questionRepository.save(q);

        String[] opts = {o1, o2, o3, o4};

        for (int i = 0; i < 4; i++) {
            Options opt = new Options();
            opt.setText(opts[i]);
            opt.setCorrect(i == correctIndex);
            opt.setQuestion(q);

            optionRepository.save(opt);
        }
    }

    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    public void update(Long id,
                       String text,
                       Long categoryId,
                       String opt1,
                       String opt2,
                       String opt3,
                       String opt4,
                       int correctIndex) {

        Question q = questionRepository.findById(id).orElse(null);

        if (q != null) {
            q.setText(text);

            Category category =
                    categoryRepository.findById(categoryId).orElse(null);

            q.setCategory(category);

            questionRepository.save(q);

            List<Options> options = q.getOptions();

            options.get(0).setText(opt1);
            options.get(1).setText(opt2);
            options.get(2).setText(opt3);
            options.get(3).setText(opt4);

            for(int i=0; i<4; i++) {
                options.get(i).setCorrect(i == correctIndex);
                optionRepository.save(options.get(i));
            }
        }
    }
    public int calculateScore(HttpServletRequest request) {

        int score = 0;

        List<Question> questions =
                questionRepository.findAll();

        for (Question q : questions) {

            String answer =
                    request.getParameter(
                            "question_" + q.getId()
                    );

            if(answer == null)
                continue;

            Long chosenOptionId =
                    Long.parseLong(answer);

            for(Options opt : q.getOptions()) {

                if(opt.getId().equals(chosenOptionId)
                        && opt.isCorrect()) {

                    score++;
                }
            }
        }

        return score;
    }
}
