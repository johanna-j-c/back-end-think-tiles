package com.johanna.controller;

import com.johanna.model.Question;
import com.johanna.exception.ResourceNotFoundException;
import com.johanna.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.QuestionRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/")

public class QuestionController {

    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    private TeacherRepository teacherRepository;

//    @GetMapping
//    public List<Question> getQuestions() {
//        return questionRepository.findAll();
//    }
    @GetMapping("teachers/{teacherId}/questions")
    public ResponseEntity<List<Question>> getAllQuestionsByTeacherId(@PathVariable(value = "teacherId") Integer teacherId) {
    if (!teacherRepository.existsById(teacherId)) {
        throw new ResourceNotFoundException("Not found Teacher with id = " + teacherId);
    }

    List<Question> questions = questionRepository.findByTeacherId(teacherId);
    return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("questions/{id}")
    public ResponseEntity<Question> getQuestionsByTeacherId(@PathVariable(value = "id") Integer id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    public record NewQuestionRequest(
            String title,
            String prompt
    ) {

    }

//    @PostMapping
//    public void addQuestion(@RequestBody QuestionController.NewQuestionRequest request) {
//        Question question = new Question();
//        question.setTitle(request.title());
//        question.setPrompt(request.prompt());
//        questionRepository.save(question);
//    }
    @PostMapping("teachers/{teacherId}/questions")
    public ResponseEntity<Question> createQuestion(@PathVariable(value = "teacherId") Integer teacherId,
                                                   @RequestBody Question questionRequest) {
    Question question = teacherRepository.findById(teacherId).map(teacher -> {
        questionRequest.setTeacher(teacher);
        return questionRepository.save(questionRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Teacher with id = " + teacherId));

    return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @PutMapping("questions/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") Integer id, @RequestBody Question questionRequest) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("QuestionId " + id + "not found"));

        question.setTitle(questionRequest.getTitle());
        question.setPrompt(questionRequest.getPrompt());

        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.OK);
    }

    @DeleteMapping("questions/{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Integer id) {
        questionRepository.deleteById(id);
    }

}
