package com.johanna.controller;

import com.johanna.model.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.TeacherRepository;
import com.johanna.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("https://front-end-think-tiles.onrender.com")
@RequestMapping("/teachers")

public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


//    @GetMapping
//    public List<Teacher> getTeacher() {
//        return teacherRepository.findAll();
//    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(@RequestParam(required = false) String name) {
    List<Teacher> teachers = new ArrayList<Teacher>();

    if (name == null)
        teacherRepository.findAll().forEach(teachers::add);
    else
        teacherRepository.findByName(name).forEach(teachers::add);

    if (teachers.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") Integer id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        return new ResponseEntity<>(teacher, HttpStatus.OK);
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

    @PutMapping("{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("teacherId") Integer id, @RequestBody Teacher teacher) {
        Teacher _teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));

        _teacher.setName(teacher.getName());
        _teacher.setEmail(teacher.getEmail());

        return new ResponseEntity<>(teacherRepository.save(_teacher), HttpStatus.OK);
    }

    @DeleteMapping("{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Integer id) {
        teacherRepository.deleteById(id);
    }

}
