package com.example.exam.models.binding;

import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class SongAddBindingModel {
    @NotBlank(message = "performer is required")
    @Size(min = 3, max = 20, message = "performer name must be between 3 and 20 symbols")
    private String performer;
    @NotBlank(message = "title is required")
    @Size(min = 2, max = 20, message = "title name must be between 3 and 20 symbols")
    private String title;
    @Positive(message = "duration must be positive number")
    @NotNull(message = "duration is required")
    private Long duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "date can not be in future")
    private LocalDate releaseDate;
    @NotNull(message = "please select a valid style")
    private StyleEnum style;

    public SongAddBindingModel() {
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

