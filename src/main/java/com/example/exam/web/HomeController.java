package com.example.exam.web;

import com.example.exam.models.entity.StyleEnum;
import com.example.exam.models.entity.User;
import com.example.exam.models.views.SongViewModel;
import com.example.exam.service.SongService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final SongService songService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public HomeController(SongService songService, ModelMapper modelMapper, UserService userService) {
        this.songService = songService;

        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(HttpSession httpSession, Model model){
        if(httpSession.getAttribute("username")==null){
            return "index";
        }

        model.addAttribute("pop", songService.getAllSongsByStyle(StyleEnum.POP));
        model.addAttribute("rock", songService.getAllSongsByStyle(StyleEnum.ROCK));
        model.addAttribute("jazz", songService.getAllSongsByStyle(StyleEnum.JAZZ));

        List<SongViewModel> playlist = userService.getPlaylist(httpSession.getAttribute("username").toString());
        model.addAttribute("totalDuration", userService.getTotalDurationByUser(httpSession.getAttribute("username").toString()));
        model.addAttribute("playlist", playlist);

        return "home";
    }
}
