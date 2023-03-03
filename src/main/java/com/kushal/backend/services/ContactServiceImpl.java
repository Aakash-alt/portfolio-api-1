package com.kushal.backend.services;

import com.kushal.backend.entities.ContactForm;
import com.kushal.backend.exceptions.EmailNotSentException;
import com.kushal.backend.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private EmailService service;

    @Autowired
    private ContactRepository repository;

    @Override
    public ContactForm sendEmailUsingContactForm(ContactForm form) throws EmailNotSentException {

        Map<String, Object> model = new HashMap<>();
        model.put("name", form.getFirstName() + " " + form.getLastName());
        if (service.sendEmail(form, model)) {
            return repository.save(form);
        }
        throw new EmailNotSentException("Some error occurred!", form);
    }
}
