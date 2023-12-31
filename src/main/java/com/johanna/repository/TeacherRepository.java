package com.johanna.repository;

import com.johanna.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TeacherRepository
        extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByName(String name);

}
