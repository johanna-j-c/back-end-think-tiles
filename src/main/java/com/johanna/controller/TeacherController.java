package com.johanna.controller;

import com.johanna.model.Teacher;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.TeacherRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/teachers")

public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @GetMapping
    public List<Teacher> getTeacher() {
        return teacherRepository.findAll();
    }

    public record NewTeacherRequest(
            String name,
            String email
    ) {

    }

    @PostMapping
    public void addTeacher(@RequestBody TeacherController.NewTeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setName(request.name());
        teacher.setEmail(request.email());
        teacherRepository.save(teacher);
    }

    @DeleteMapping("{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Integer id) {
        teacherRepository.deleteById(id);
    }

}
