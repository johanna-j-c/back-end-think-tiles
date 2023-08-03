package com.johanna.repository;

import com.johanna.model.Tile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TileRepository
        extends JpaRepository<Tile, Integer> {
}
