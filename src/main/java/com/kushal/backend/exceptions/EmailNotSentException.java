package com.kushal.backend.exceptions;

import com.kushal.backend.entities.ContactForm;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailNotSentException extends Exception {

    ContactForm form;
    public EmailNotSentException() {
        super();
    }

    public EmailNotSentException(String message, ContactForm form) {
        super(message);
        this.form = form;
    }

    public EmailNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailNotSentException(Throwable cause) {
        super(cause);
    }

    protected EmailNotSentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
