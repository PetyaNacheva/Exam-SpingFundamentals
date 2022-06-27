package com.example.exam.models.service;

import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class SongServiceModel {

    private String performer;

    private String title;

    private Long duration;

    private LocalDate releaseDate;

    private StyleEnum style;

    public SongServiceModel() {
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }
}
