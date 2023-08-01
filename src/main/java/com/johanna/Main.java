package com.johanna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/tiles")
public class Main {
    private final TileRepository tileRepository;

    public Main(TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Tile> getTiles() {
        return tileRepository.findAll();
    }

    record NewTileRequest(
            String title,
            String prompt
    ) {

    }

    @PostMapping
    public void addTile(@RequestBody NewTileRequest request) {
        Tile tile = new Tile();
        tile.setTitle(request.title());
        tile.setPrompt(request.prompt());
        tileRepository.save(tile);
    }

}
