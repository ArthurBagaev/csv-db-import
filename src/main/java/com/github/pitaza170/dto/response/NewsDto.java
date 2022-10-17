package com.github.pitaza170.dto.response;

import com.github.pitaza170.model.News;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class NewsDto {

    private Long id;
    private String links;
    private String title;
    private String role;
    private String text;

    public NewsDto(News news) {
        this.id = news.getId();
        this.links = news.getLinks();
        this.title = news.getTitle();
        this.role = news.getRole();
        this.text = news.getText();
    }


}
