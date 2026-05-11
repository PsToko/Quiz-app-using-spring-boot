package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue
    Long id;

    String name;


    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
