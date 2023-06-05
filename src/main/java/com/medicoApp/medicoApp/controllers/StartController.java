package com.medicoApp.medicoApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping("/")
    public String showHomePage(){
        return "homePage";
    }
    @GetMapping("/offer")
    public String showOfferPage(){
        return "offerPage";
    }
}
