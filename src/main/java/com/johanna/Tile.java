package com.johanna;

import jakarta.persistence.*;
import org.springframework.validation.ObjectError;

import java.util.Objects;

@Entity
public class Tile {

    @Id
    @SequenceGenerator(
            name = "tile_id_sequence",
            sequenceName = "tile_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tile_id_sequence"
    )
    private Integer id;
    private String title;
    private String prompt;

    public Tile(Integer id,
                String title,
                String prompt) {
        this.id = id;
        this.title = title;
        this.prompt = prompt;
    }

    public Tile() {

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(id, tile.id) && Objects.equals(title, tile.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, prompt);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", prompt='" + prompt + '\'' +
                '}';
    }
}


