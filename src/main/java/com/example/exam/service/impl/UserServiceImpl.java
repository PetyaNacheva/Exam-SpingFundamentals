package com.example.exam.service.impl;

import com.example.exam.models.entity.Song;
import com.example.exam.models.entity.User;
import com.example.exam.models.service.UserServiceModel;
import com.example.exam.models.views.SongViewModel;
import com.example.exam.repository.UserRepository;
import com.example.exam.service.SongService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final SongService songService;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, SongService songService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.songService = songService;
    }

    @Override
    public boolean saveUser(UserServiceModel userServiceModel) {
        try {
            userRepository.save(modelMapper.map(userServiceModel, User.class));

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
      return userRepository.findByUsernameAndPassword(username, password).map(u->modelMapper.map(u, UserServiceModel.class)).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<SongViewModel> getPlaylist(String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        return user.getPlaylist().stream().map(s->{
            SongViewModel song = modelMapper.map(s, SongViewModel.class);
            song.setDuration(durationFormatter(s.getDuration()));
            return song;
        }).collect(Collectors.toList());
    }

    private String durationFormatter(Long durationInSeconds){
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
    public String getTotalDurationByUser(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        Long totalDuration = 0L;
        for (Song song : user.getPlaylist()) {
            totalDuration+= song.getDuration();
        }
        return durationFormatter(totalDuration);
    }

    @Override
    public void addSongToPlaylist(Long id, User user) {
        Song song = songService.findById(id);

        Set<Song> songs = user.getPlaylist();

            songs.add(song);
            user.setPlaylist(songs);
            userRepository.save(user);

    }

    @Override
    public void deletePlaylist(User user) {
        Set<Song> playlist = user.getPlaylist();
        playlist.clear();
        user.setPlaylist(playlist);
        userRepository.save(user);
    }
}
