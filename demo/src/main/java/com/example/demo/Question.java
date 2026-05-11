package com.example.demo;

import com.example.demo.Category;
import com.example.demo.Options;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne
    private Category category;
    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Options> options;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Options> getOptions() {
        return options;
    }

    public Category getCategory() {
        return category;
    }

    // SETTERS

    public void setText(String text) {
        this.text = text;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }
}