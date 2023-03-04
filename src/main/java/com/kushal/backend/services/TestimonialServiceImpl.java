package com.kushal.backend.services;

import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;
import com.kushal.backend.repositories.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository repository;
    @Override
    public List<Testimonial> getTestimonialsList() {
        return repository.findAll();
    }

    @Override
    public Testimonial postTestimonial(Testimonial testimonial) throws IncorrectDataException {
        if(testimonial.getDescription() != null && testimonial.getQuotedBy() != null) {
            return repository.save(testimonial);
        }
        throw new IncorrectDataException("Please provide all the necessary fields!");
    }
}

