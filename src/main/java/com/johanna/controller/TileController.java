package com.johanna.controller;

import com.johanna.model.Tile;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.TileRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/tiles")

public class TileController {

    private final TileRepository tileRepository;

    public TileController(TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }


    @GetMapping
    public List<Tile> getTiles() {
        return tileRepository.findAll();
    }

    public record NewTileRequest(
            String title,
            String prompt
    ) {

    }

    @PostMapping
    public void addTile(@RequestBody TileController.NewTileRequest request) {
        Tile tile = new Tile();
        tile.setTitle(request.title());
        tile.setPrompt(request.prompt());
        tileRepository.save(tile);
    }

    @DeleteMapping("{tileId}")
    public void deleteTile(@PathVariable("tileId") Integer id) {
        tileRepository.deleteById(id);
    }

}

