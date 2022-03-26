package org.launchcode.GermanLessonDatabase.controllers;
import java.io.IOException;
import java.util.stream.Collectors;

import org.launchcode.GermanLessonDatabase.LessonsUpload;
import org.launchcode.GermanLessonDatabase.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("lessons")
public class LessonController {

    private static List<String> lessons = new ArrayList<>();
    private final LessonsUpload lessonsUpload;

    @Autowired
    public FileUploadController(LessonsUpload lessonsUpload){
        this.lessonsUpload = lessonsUpload;
    }
    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", lessonsUpload.loadAll().map(
                path ->
                        MvcUriComponentsBuilder.fromMethodName(LessonController.class,
                "serveFile",
                                path.getFilename().toString()).build().toURI().toString()).collect(Collectors.toList()));
            return "addLesson";
    }

    @GetMapping("/files/{filename: .+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = lessonsUpload.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() +
                "\"").body(file);
    }

   @PostMapping("/")
   public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes) {
       lessonsUpload.store(file);
       redirectAttributes.addFlashAttribute("message", "You successfully uploaded" + file.getOrginalFilename() + "!");
       return "redirect:/";
   }
   @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
       return ResponseEntity.notFound().build();
   }

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
    public String addNewLesson(@RequestParam String lessonTitle, @RequestParam String description, @RequestParam byte[] files,
                               @RequestParam String discussionQuestions){
        lessons.add(lessonTitle);
        return "redirect:/lessons/index";

    }

}
