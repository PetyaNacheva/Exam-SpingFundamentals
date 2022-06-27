package com.example.exam.web;

import com.example.exam.models.binding.UserLoginBindingModel;
import com.example.exam.models.binding.UserRegisterBindingModel;
import com.example.exam.models.entity.Song;
import com.example.exam.models.entity.User;
import com.example.exam.models.service.UserServiceModel;
import com.example.exam.service.SongService;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final SongService songService;

    public UserController(UserService userService, ModelMapper modelMapper, SongService songService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.songService = songService;
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("isExist", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirmed(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, HttpSession httpSession){
        if(bindingResult.hasErrors()||!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        if(httpSession.getAttribute("username")!=null){
            return "redirect:/";
        }

        boolean isSaved=userService.saveUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        if(!isSaved){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("isExist", true);
            return "redirect:register";
        }
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
            model.addAttribute("notFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirmed(@Valid UserLoginBindingModel userLoginBindingModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 HttpSession httpSession){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel" , userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        if(httpSession.getAttribute("username")!=null){
            return "redirect:/";
        }
        UserServiceModel user =userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword());
        if(user==null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:login";
        }
        httpSession.setAttribute("username", user.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/addToPlaylist/{id}")
    public String addToPlaylist(@PathVariable Long id, HttpSession httpSession){
        if(httpSession.getAttribute("username")==null){
            return "redirect:login";
        }
        
        User user =userService.findByUsername(httpSession.getAttribute("username").toString());
              userService.addSongToPlaylist(id, user);
       return "redirect:/";
    }

    @GetMapping("/removePlaylist")
    public String deletePlaylist(HttpSession httpSession){
        if(httpSession.getAttribute("username")==null){
            return "redirect:login";
        }
        User user =userService.findByUsername(httpSession.getAttribute("username").toString());
        userService.deletePlaylist(user);

        return "redirect:/";
    }
}
