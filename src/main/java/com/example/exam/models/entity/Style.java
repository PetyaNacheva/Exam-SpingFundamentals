package com.example.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Style() {
    }

    public StyleEnum getName() {
        return name;
    }

    public void setName(StyleEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
