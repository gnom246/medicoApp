package com.medicoApp.medicoApp.controllers;

import com.medicoApp.medicoApp.services.VisitsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VisitsController {
    VisitsService visitsService;

    public VisitsController(VisitsService visitsService){
        this.visitsService= visitsService;
    }


    @GetMapping("/for-user/{userId}/visits")
    public String showSpec(@PathVariable Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("specList", visitsService.showAllSpec());
        return "visitsPage";
    }
    @PostMapping("/for-user/{userId}/visits")
    public String getSpec(@PathVariable Long userId, @RequestParam String specialist){
        return "redirect:/for-user/"+ userId+ "/visits/"+ specialist;
    }
    @GetMapping("/for-user/{userId}/visits/{specialist}")
    public String showVisits(@PathVariable Long userId, @PathVariable String specialist, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("spec", specialist);
        model.addAttribute("specList", visitsService.showAllSpec());
        model.addAttribute("visitsList", visitsService.showFreeVisits(specialist));
        return "visitsForSpecPage";
    }
    @PostMapping("/for-user/{userId}/visits/{specialist}/{visitId}")
    public String bookVisit(@PathVariable Long userId, @PathVariable String specialist, @PathVariable Long visitId){
        System.out.println(specialist);
        System.out.println(visitId);
        visitsService.book(userId, visitId);
        return "bookedPage";
    }
}
