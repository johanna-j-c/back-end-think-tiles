package com.johanna.controller;

import com.johanna.model.Question;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.QuestionRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/questions")

public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @GetMapping
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public record NewQuestionRequest(
            String title,
            String prompt
    ) {

    }

    @PostMapping
    public void addQuestion(@RequestBody QuestionController.NewQuestionRequest request) {
        Question question = new Question();
        question.setTitle(request.title());
        question.setPrompt(request.prompt());
        questionRepository.save(question);
    }

    @DeleteMapping("{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Integer id) {
        questionRepository.deleteById(id);
    }

}
