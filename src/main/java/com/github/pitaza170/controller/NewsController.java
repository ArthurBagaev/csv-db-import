package com.github.pitaza170.controller;

import com.github.pitaza170.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.github.pitaza170.message.ResponseMessage;
import com.github.pitaza170.service.NewsService;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getAllNews() {
        try {
            List<News> news = newsService.findAll();

            if (news.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            //return ResponseEntity.status(HttpStatus.OK).body(news);
            return new ResponseEntity<>(news, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        newsService.create(file);
        String message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/news/{role}")
    public ResponseEntity<List<News>> findByRole(@PathVariable String role) {
        List<News> news = newsService.findByRole(role);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @DeleteMapping("/news")
    public ResponseEntity<ResponseMessage> deleteAll() {
        newsService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("succesfully deleted"));
    }



}
