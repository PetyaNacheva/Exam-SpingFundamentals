package com.example.exam.service.impl;

import com.example.exam.models.entity.Song;
import com.example.exam.models.entity.Style;
import com.example.exam.models.entity.StyleEnum;
import com.example.exam.models.entity.User;
import com.example.exam.models.service.SongServiceModel;
import com.example.exam.models.service.UserServiceModel;
import com.example.exam.models.views.SongViewModel;
import com.example.exam.repository.SongRepository;
import com.example.exam.service.SongService;
import com.example.exam.service.StyleService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
   private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final SongRepository songRepository;


    public SongServiceImpl(ModelMapper modelMapper, StyleService styleService, SongRepository songRepository) {
        this.modelMapper = modelMapper;
        this.styleService = styleService;
        this.songRepository = songRepository;

    }



    @Override
    public void addSong(SongServiceModel songServiceModel) {
        Song song = modelMapper.map(songServiceModel, Song.class);
        Style style = styleService.findByName(songServiceModel.getStyle());
        song.setStyle(style);
        songRepository.save(song);
    }

    @Override
    public boolean findByPerformer(String performer) {
        Song song =songRepository.findByPerformer(performer).orElse(null);
        if(song==null){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<SongViewModel> getAllSongsByStyle(StyleEnum name) {
        return songRepository.findAllByStyle_Name(name).stream().map(s->{
            SongViewModel songViewModel = modelMapper.map(s, SongViewModel.class);

            songViewModel.setDuration(durationFormatter(s.getDuration()));
       return songViewModel;
        }).collect(Collectors.toList());
    }

    public String durationFormatter(Long durationInSeconds){
        long seconds = durationInSeconds%60;
        long minutes = (durationInSeconds-seconds)/60;
        String secondsInString="";
        if(seconds<10){

            secondsInString ="0"+String.valueOf(seconds);
        }else{
            secondsInString=String.valueOf(seconds);
        }
        return minutes +":"+secondsInString;
    }

    @Override
    public Song findById(Long id) {
        return  songRepository.findById(id).orElse(null);
    }


}
