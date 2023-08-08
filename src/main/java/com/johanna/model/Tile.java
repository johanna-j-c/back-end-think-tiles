package com.johanna.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String value;
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;


    public Tile(Integer id,
                String value,
                Integer displayOrder) {
        this.id = id;
        this.value = value;
        this.displayOrder = displayOrder;
    }

    public Tile() {

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() { return value; }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(id, tile.id) && Objects.equals(value, tile.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, displayOrder);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", display order='" + displayOrder + '\'' +
                '}';
    }
}


