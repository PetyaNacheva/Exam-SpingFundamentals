package com.example.exam.service;

import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;

public interface StyleService {
    void initStyles();

    Style findByName(StyleEnum style);
}
