package com.kushal.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponse<T> {
    private HttpStatus status;
    private T deletedResponse;
    private String message;
    private Long timeStamp;
}
