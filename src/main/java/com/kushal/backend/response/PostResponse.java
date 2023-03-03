package com.kushal.backend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse<T> {
    private HttpStatus status;
    private T postedResponse;
    private String message;
    private Long timeStamp;
}
