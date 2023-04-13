package com.kushal.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateResponse<T> {
    private HttpStatus status;
    private String updatedOf;
    private T updatedResponse;
    private String message;
    private Long timeStamp;
}
