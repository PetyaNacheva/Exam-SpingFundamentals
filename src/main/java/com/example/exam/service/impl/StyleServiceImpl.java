package com.example.exam.service.impl;

import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;
import com.example.exam.repository.StyleRepository;
import com.example.exam.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;


    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if(styleRepository.count()==0){
            Arrays.stream(StyleEnum.values()).forEach(s->{
                Style style = new Style();
                style.setName(s);
                style.setDescription("Description of "+s);
                styleRepository.save(style);
            });
        }
    }

    @Override
    public Style findByName(StyleEnum style) {
       return styleRepository.findByName(style).orElse(null);
    }
}
