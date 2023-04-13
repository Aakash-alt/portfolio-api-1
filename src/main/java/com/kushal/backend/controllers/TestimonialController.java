package com.kushal.backend.controllers;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Testimonial;
import com.kushal.backend.exceptions.IncorrectDataException;
import com.kushal.backend.exceptions.NotFoundException;
import com.kushal.backend.response.DeleteResponse;
import com.kushal.backend.response.PostResponse;
import com.kushal.backend.response.UpdateResponse;
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

    @PutMapping
    public UpdateResponse<Testimonial> updateTestimonialUsingId(
            @RequestParam("projectId") String projectId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "quotedBy", required = false) String quotedBy
    ) throws IncorrectDataException, NotFoundException {
        Testimonial updatedTestimonial = service.updateTestimonial(projectId, description, quotedBy);
        return new UpdateResponse<>(
                HttpStatus.OK,
                projectId,
                updatedTestimonial,
                "Testimonial with id " + projectId + " was updated successfully!",
                Constant.getTimeStamp()
        );
    }

    @DeleteMapping
    public DeleteResponse<Testimonial> deleteTestimonialUsingId(
            @RequestParam("projectId") String projectId
    ) throws NotFoundException {
        Testimonial deletedTestimonial = service.deleteTestimonial(projectId);
        return new DeleteResponse<>(
                HttpStatus.OK,
                deletedTestimonial,
                "Testimonial with id " + projectId + " is deleted successfully!",
                Constant.getTimeStamp()
        );
    }
}
