package com.github.pitaza170.controller;

import com.github.pitaza170.dto.response.NewsDto;
import com.github.pitaza170.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
        List<NewsDto> news = newsService.findAll();
        model.addAttribute("news", news);
        return "news_feed";
    }


    @GetMapping("/news/{role}")
    public String findByRole(@PathVariable("role") String role, Model model) {
        List<NewsDto> newsByRole = newsService.findByRole(role);
        model.addAttribute("news", newsByRole);
        return "news_feed";
    }


}
