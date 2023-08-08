package com.johanna.repository;

import com.johanna.model.Question;
import com.johanna.model.Tile;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

public interface TileRepository
        extends JpaRepository<Tile, Integer> {
    List<Tile> findByQuestionId(Integer questionId);

    @Transactional
    void deleteByQuestionId(Integer questionId);
}
