package com.kushal.backend.exceptions;

import com.kushal.backend.response.EmailSentResponse;
import com.kushal.backend.response.IncorrectResponse;
import com.kushal.backend.response.NotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundResponse> notFoundExceptionHandler(NotFoundException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        NotFoundResponse
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(e.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(EmailNotSentException.class)
    public ResponseEntity<EmailSentResponse> emailNotSentExceptionHandler(EmailNotSentException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        EmailSentResponse
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(e.getMessage())
                                .contactForm(e.getForm())
                                .build()
                );
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<IncorrectResponse> incorrectDataExceptionHandler(IncorrectDataException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        IncorrectResponse
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(e.getMessage())
                                .build())
                ;
    }
}
