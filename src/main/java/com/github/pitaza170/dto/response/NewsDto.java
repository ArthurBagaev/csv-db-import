package com.github.pitaza170.dto.response;

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

}
