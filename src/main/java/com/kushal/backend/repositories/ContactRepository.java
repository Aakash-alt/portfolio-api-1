package com.kushal.backend.repositories;

import com.kushal.backend.entities.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactForm, Long> {
}
