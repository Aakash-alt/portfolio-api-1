package com.kushal.backend.repositories;

import com.kushal.backend.entities.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {
}
