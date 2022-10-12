package com.github.pitaza170.controller;

import com.github.pitaza170.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.github.pitaza170.message.ResponseMessage;
import com.github.pitaza170.service.NewsService;

import java.util.List;

@Controller
@RequestMapping("/feed")
public class JsonLoadController {

    private final NewsService newsService;

    @Autowired
    public JsonLoadController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String getAllNews(Model model) {
        List<News> news = newsService.findAll();
        model.addAttribute("news", news);
        return "news_feed";
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        newsService.create(file);
        String message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/news/{role}")
    public String findByRole(@PathVariable("role") String role, Model model) {
        List<News> newsByRole = newsService.findByRole(role);
        model.addAttribute("news", newsByRole);
        return "news_feed";
    }

    @DeleteMapping("/news")
    public ResponseEntity<ResponseMessage> deleteAll() {
        newsService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("succesfully deleted"));
    }


}
