package com.kushal.backend.services;

import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;
import com.kushal.backend.exceptions.NotFoundException;
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
        if (testimonial.getDescription() != null && testimonial.getQuotedBy() != null) {
            return repository.save(testimonial);
        }
        throw new IncorrectDataException("Please provide all the necessary fields!");
    }

    @Override
    public Testimonial updateTestimonial(String projectId, String description, String quotedBy) throws NotFoundException {
        Testimonial testimonial = repository.findById(Long.valueOf(projectId)).orElseThrow(() -> new NotFoundException("No project with id " + projectId + " found!"));
        if (description != null && !description.equals("")) {
            testimonial.setDescription(description);
        }
        if (quotedBy != null && !quotedBy.equals("")) {
            testimonial.setQuotedBy(quotedBy);
        }
        return repository.save(testimonial);
    }

    @Override
    public Testimonial deleteTestimonial(String projectId) throws NotFoundException {
        Testimonial testimonial = repository.findById(Long.valueOf(projectId)).orElseThrow(() -> new NotFoundException("No project with id " + projectId + " found!"));
        repository.delete(testimonial);
        return testimonial;
    }
}

