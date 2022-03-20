package org.launchcode.GermanLessonDatabase.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("lessons")
public class LessonController {

    private static List<String> lessons = new ArrayList<>();


    @GetMapping
    public String displayAllLessons(Model model){
        List<String> lessons = new ArrayList<>();
        model.addAttribute("lessons", lessons);
        return "lessons/index";
    }

    @GetMapping("addLesson")
    public String displayAddLessonForm(){
        return "lessons/addLesson";
    }

    @PostMapping("addLesson")
    public String addNewLesson(@RequestParam String lessonTitle, @RequestParam String description, @RequestParam byte[] readingMaterials,
                               @RequestParam byte[] worksheet, @RequestParam String discussionQuestions){
        lessons.add(lessonTitle);
        return "redirect:/lessons/index";

    }

}
