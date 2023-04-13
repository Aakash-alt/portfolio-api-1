package com.kushal.backend.services;

import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;
import com.kushal.backend.exceptions.NotFoundException;

import java.util.List;

public interface TestimonialService {
    List<Testimonial> getTestimonialsList();

    Testimonial postTestimonial(Testimonial testimonial) throws IncorrectDataException;

    Testimonial updateTestimonial(String projectId, String description, String quotedBy) throws IncorrectDataException, NotFoundException;

    Testimonial deleteTestimonial(String projectId) throws NotFoundException;
}
