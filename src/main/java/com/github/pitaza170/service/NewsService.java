package com.github.pitaza170.service;

import com.github.pitaza170.dto.mapper.NewsRequestMapper;
import com.github.pitaza170.dto.response.NewsDto;
import com.github.pitaza170.model.News;
import com.github.pitaza170.util.CSVHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.pitaza170.repository.NewsRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.github.pitaza170.common.Constants.FAILED_DATA;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDto> findAll() {
        return newsRepository.findAll().stream()
                .map(NewsDto::new)
                .toList();
    }

    public List<NewsDto> findByRole(String role) {
        return newsRepository.findByRole(role).stream()
                .map(NewsDto::new)
                .toList();
    }

    @SneakyThrows
    public void create(MultipartFile file) throws EntityNotFoundException {
        final List<News> news = CSVHelper.csvToNews(file.getInputStream()).stream()
                .map(NewsRequestMapper::mapToEntity)
                .toList();

        if (news.isEmpty()) {
            throw new EntityNotFoundException(FAILED_DATA);
        }
        newsRepository.saveAll(news);
    }

    public void deleteAll() {
        newsRepository.deleteAll();
    }
}
