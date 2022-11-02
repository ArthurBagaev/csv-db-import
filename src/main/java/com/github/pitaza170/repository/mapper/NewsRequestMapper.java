package com.github.pitaza170.repository.mapper;

import com.github.pitaza170.dto.request.NewsRequest;
import com.github.pitaza170.model.News;

public class NewsRequestMapper {

    public static News mapToEntity(NewsRequest newsRequest) {
        return new News(
                newsRequest.getId(),
                newsRequest.getLinks(),
                newsRequest.getTitle(),
                newsRequest.getRole(),
                newsRequest.getText());
    }

}
