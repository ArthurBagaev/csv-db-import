package com.github.pitaza170.controller;

import com.github.pitaza170.dto.response.ApiResponse;
import com.github.pitaza170.dto.response.NewsDto;
import com.github.pitaza170.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.github.pitaza170.service.NewsService;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.github.pitaza170.common.Constants.*;

@Validated
@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final Date time;


    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<NewsDto>> uploadFile(@RequestParam("file") MultipartFile file) {
        newsService.create(file);
        return ResponseEntity
                .ok(new ApiResponse<>(time, SUCCESSFULLY_UPLOADED));
    }

    @GetMapping("/news")
    public ResponseEntity<ApiResponse<List<NewsDto>>> getAllNews() {
        final List<NewsDto> news = newsService.findAll();
        return ResponseEntity
                .ok(new ApiResponse<>(time, SUCCESS, news));
    }

    @GetMapping("/news/{role}")
    public ResponseEntity<ApiResponse<List<NewsDto>>> findByRole(@PathVariable String role) {
        final List<NewsDto> newsByRole = newsService.findByRole(role);
        return ResponseEntity
                .ok(new ApiResponse<>(time, SUCCESS, newsByRole));
    }

    @DeleteMapping("/news")
    public ResponseEntity<ApiResponse<NewsDto>> deleteAll() {
        newsService.deleteAll();
        return ResponseEntity
                .ok(new ApiResponse<>(time, SUCCESSFULLY_DELETED));
    }


}
