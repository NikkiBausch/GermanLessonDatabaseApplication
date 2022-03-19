package org.launchcode.GermanLessonDatabase.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("lessons")
public class LessonController {

    @GetMapping
    public String displayAllLessons(Model model){
        List<String> lessons = new ArrayList<>();
        model.addAttribute("lessons", lessons);
        return "lessons/index";
    }
}
