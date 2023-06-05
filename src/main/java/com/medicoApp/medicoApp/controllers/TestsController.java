package com.medicoApp.medicoApp.controllers;

import com.medicoApp.medicoApp.services.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestsController {
    TestService testService;

    public TestsController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/for-user/{userId}/tests")
    public String showTestResult(@PathVariable Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("tests", testService.findAllUsersTests(userId));

        return "testsPage";
    }

    @GetMapping("/for-user/{userId}/tests/{testId}")
    public String showTestResult(@PathVariable Long userId, @PathVariable Long testId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("testDesc", testService.findTestDesc(testId).get());
        return "testDesc";
    }
}
