package com.kushal.backend.services;

import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;

import java.util.List;

public interface TestimonialService {
    List<Testimonial> getTestimonialsList();

    Testimonial postTestimonial(Testimonial testimonial) throws IncorrectDataException;
}
