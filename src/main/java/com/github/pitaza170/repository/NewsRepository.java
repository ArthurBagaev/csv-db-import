package com.github.pitaza170.repository;

import com.github.pitaza170.dto.response.NewsDto;
import com.github.pitaza170.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository<T> extends JpaRepository<T, Long> {
    List<T> findByRole(String role);
}

