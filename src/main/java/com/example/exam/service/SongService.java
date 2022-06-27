package com.example.exam.service;

import com.example.exam.models.entity.Song;
import com.example.exam.models.entity.StyleEnum;
import com.example.exam.models.service.SongServiceModel;
import com.example.exam.models.views.SongViewModel;

import java.util.List;

public interface SongService {
    void addSong(SongServiceModel map);

    boolean findByPerformer(String performer);

    List<SongViewModel> getAllSongsByStyle(StyleEnum pop);

    Song findById(Long id);

}
