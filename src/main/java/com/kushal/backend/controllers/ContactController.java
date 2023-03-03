package com.kushal.backend.controllers;

import com.kushal.backend.entities.ContactForm;
import com.kushal.backend.exceptions.EmailNotSentException;
import com.kushal.backend.response.EmailSentResponse;
import com.kushal.backend.services.ContactService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public EmailSentResponse sendEmailUsingContactForm(
            @RequestBody ContactForm form
    ) throws EmailNotSentException, MessagingException {
        ContactForm contactForm = contactService.sendEmailUsingContactForm(form);
        return EmailSentResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Email sent!")
                .contactForm(contactForm)
                .build();
    }
}
