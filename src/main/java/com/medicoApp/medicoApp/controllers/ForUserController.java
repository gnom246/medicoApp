package com.medicoApp.medicoApp.controllers;

import com.medicoApp.medicoApp.services.UserService;
import com.medicoApp.medicoApp.services.VisitsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ForUserController {
    UserService userService;
    VisitsService visitsService;

    public ForUserController(UserService userService, VisitsService visitsService) {
        this.userService = userService;
        this.visitsService = visitsService;
    }

    @GetMapping("/for-user/{userId}")
    public String showUserPage(@PathVariable Long userId, Model model){
        model.addAttribute("ifLogged", userService.isLogged());
        model.addAttribute("loggedUser", userService.getUserDetails(userId).get());
        model.addAttribute("userVisits", visitsService.getNearestVisit(userId));
        return "for-user";
    }
}
