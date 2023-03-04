package com.kushal.backend.controllers;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;
import com.kushal.backend.response.PostResponse;
import com.kushal.backend.services.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("api/v1/testimonial"))
public class TestimonialController {

    @Autowired
    private TestimonialService service;

    @GetMapping
    public List<Testimonial> getListOfTestimonials() {
        List<Testimonial> testimonials = service.getTestimonialsList();
        return testimonials.size() > 0 ? testimonials : List.of(new Testimonial());
    }

    @PostMapping
    public PostResponse<Testimonial> postTestimonial(@RequestBody Testimonial testimonial) throws IncorrectDataException {
        Testimonial postedTestimonial = service.postTestimonial(testimonial);
        return new PostResponse<>(
                HttpStatus.OK,
                postedTestimonial,
                testimonial.getId() + " posted successfully!",
                Constant.getTimeStamp()
        );
    }
}
