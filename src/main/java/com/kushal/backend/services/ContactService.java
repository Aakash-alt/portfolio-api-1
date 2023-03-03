package com.kushal.backend.services;

import com.kushal.backend.entities.ContactForm;
import com.kushal.backend.exceptions.EmailNotSentException;
import jakarta.mail.MessagingException;

public interface ContactService {

    ContactForm sendEmailUsingContactForm(ContactForm form) throws EmailNotSentException, MessagingException;
}
