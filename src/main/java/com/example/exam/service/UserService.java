package com.example.exam.service;

import com.example.exam.models.entity.User;
import com.example.exam.models.service.UserServiceModel;
import com.example.exam.models.views.SongViewModel;

import java.util.List;

public interface UserService {
    boolean saveUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    List<SongViewModel> getPlaylist(String username);

    String getTotalDurationByUser(String username);

    void addSongToPlaylist(Long id, User user);

    void deletePlaylist(User user);
}
