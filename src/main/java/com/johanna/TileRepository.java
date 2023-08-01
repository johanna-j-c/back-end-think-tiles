package com.johanna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TileRepository
        extends JpaRepository<Tile, Integer> {
}
