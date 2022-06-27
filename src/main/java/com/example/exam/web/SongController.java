package com.example.exam.web;

import com.example.exam.models.binding.SongAddBindingModel;
import com.example.exam.models.service.SongServiceModel;
import com.example.exam.service.SongService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final ModelMapper modelMapper;

    public SongController(SongService songService, ModelMapper modelMapper) {
        this.songService = songService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession httpSession){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/users/login";
        }
        if(!model.containsAttribute("songAddBindingModel")){
            model.addAttribute("songAddBindingModel", new SongAddBindingModel());
            model.addAttribute("isExist", false);
        }
        return "song-add";
    }

    @PostMapping("/add")
    public String addConfirmed(@Valid SongAddBindingModel songAddBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, HttpSession httpSession){
        if(httpSession.getAttribute("username")==null){
            return "redirect:/users/login";
        }
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.songAddBindingModel", bindingResult);
            return "redirect:add";
        }
        boolean isExist = songService.findByPerformer(songAddBindingModel.getPerformer());
        if(isExist){
            redirectAttributes.addFlashAttribute("songAddBindingModel", songAddBindingModel);
            redirectAttributes.addFlashAttribute("isExist", true);
            return "redirect:add";
        }

        songService.addSong(modelMapper.map(songAddBindingModel, SongServiceModel.class));
        return "redirect:/";
    }
}
