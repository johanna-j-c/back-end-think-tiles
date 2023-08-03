package com.johanna.repository;

import com.johanna.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository
        extends JpaRepository<Teacher, Integer> {
}
