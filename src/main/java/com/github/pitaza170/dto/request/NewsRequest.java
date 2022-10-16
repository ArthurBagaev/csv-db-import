package com.github.pitaza170.dto.request;

import lombok.Value;

@Value
public class NewsRequest {

    Long id;
    String links;
    String title;
    String role;
    String text;

}
