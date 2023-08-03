package com.johanna.model;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_id_sequence",
            sequenceName = "teacher_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_id_sequence"
    )
    private Integer id;
    private String name;
    private String email;

    public Teacher(Integer id,
                String name,
                String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Teacher() {

    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
