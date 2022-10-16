package com.github.pitaza170.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date timestamp;
    private final String message;
    private final T data;

    public ApiResponse(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
        this.data = null;
    }

}
