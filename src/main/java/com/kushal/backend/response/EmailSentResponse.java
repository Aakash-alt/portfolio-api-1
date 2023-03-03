package com.kushal.backend.response;

import com.kushal.backend.entities.ContactForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailSentResponse {

    private HttpStatus status;
    private ContactForm contactForm;
    private String message;
}
