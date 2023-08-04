package com.johanna.repository;

import com.johanna.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import java.util.List;


public interface QuestionRepository
        extends JpaRepository<Question, Integer> {
    List<Question> findByTeacherId(Integer teacherId);

    @Transactional
    void deleteByTeacherId(Integer teacherId);
}
