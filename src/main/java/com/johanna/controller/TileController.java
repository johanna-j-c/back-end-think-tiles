package com.johanna.controller;

import com.johanna.model.Question;
import com.johanna.model.Tile;
import com.johanna.exception.ResourceNotFoundException;
import com.johanna.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;
import com.johanna.repository.TileRepository;
import com.johanna.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
//@CrossOrigin("http://localhost:3000")
@CrossOrigin("https://front-end-think-tiles.onrender.com")
@RequestMapping("/")

public class TileController {

    private final TileRepository tileRepository;

    public TileController(TileRepository tileRepository) {
        this.tileRepository = tileRepository;
    }

    @Autowired
    private QuestionRepository questionRepository;

//    @GetMapping
//    public List<Tile> getTiles() {
//        return tileRepository.findAll();
//    }
    @GetMapping("questions/{questionId}/tiles")
    public ResponseEntity<List<Tile>> getAllTilesByQuestionId(@PathVariable(value = "questionId") Integer questionId) {
    if (!questionRepository.existsById(questionId)) {
        throw new ResourceNotFoundException("Not found Question with id = " + questionId);
    }

    List<Tile> tiles = tileRepository.findByQuestionId(questionId);
    return new ResponseEntity<>(tiles, HttpStatus.OK);
    }

    public record NewTileRequest(
            String title,
            String prompt
    ) {

    }

//    @PostMapping
//    public void addTile(@RequestBody TileController.NewTileRequest request) {
//        Tile tile = new Tile();
//        tile.setTitle(request.title());
//        tile.setPrompt(request.prompt());
//        tileRepository.save(tile);
//    }
//
//    @DeleteMapping("{tileId}")
//    public void deleteTile(@PathVariable("tileId") Integer id) {
//        tileRepository.deleteById(id);
//    }
    @PostMapping("questions/{questionId}/tiles")
    public ResponseEntity<Tile> createTile(@PathVariable(value = "questionId") Integer questionId,
                                               @RequestBody Tile tileRequest) {
    Tile tile = questionRepository.findById(questionId).map(question -> {
        tileRequest.setQuestion(question);
        return tileRepository.save(tileRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Question with id = " + questionId));

    return new ResponseEntity<>(tile, HttpStatus.CREATED);
    }

    @PutMapping("tiles/{id}")
    public ResponseEntity<Tile> updateTile(@PathVariable("id") Integer id, @RequestBody Tile tileRequest) {
        Tile tile = tileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TileId " + id + "not found"));

        tile.setValue(tileRequest.getValue());
        tile.setDisplayOrder(tileRequest.getDisplayOrder());

        return new ResponseEntity<>(tileRepository.save(tile), HttpStatus.OK);
    }

    @DeleteMapping("tiles/{tileId}")
    public void deleteTile(@PathVariable("tileId") Integer id) {
        tileRepository.deleteById(id);
    }

}

