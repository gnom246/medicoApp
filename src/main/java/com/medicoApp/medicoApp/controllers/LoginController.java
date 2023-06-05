package com.medicoApp.medicoApp.controllers;

import com.medicoApp.medicoApp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    UserService userService;
    public LoginController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String userName, @RequestParam String userPassword){
        userService.saveUser(userName, userPassword);
        Long userId;
        try {
            userService.userLogin(userName, userPassword);
            userId = userService.getLoggedUserId();
        } catch (Exception e){
            return "redirect:/";
        }
        return "redirect:/for-user/"+ userId;
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "loginPage";
    }

    @PostMapping("/login")
    public String logUser(@RequestParam String userName, @RequestParam String userPassword){
        Long userId;
        try {
            userService.userLogin(userName, userPassword);
            userId = userService.getLoggedUserId();
        } catch (Exception e){
            return "redirect:/login";
        }
        return "redirect:/for-user/" + userId;
    }
}
