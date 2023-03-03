package com.kushal.backend.repositories;

import com.kushal.backend.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<List<Project>> findByTitle(String title);
    Optional<List<Project>> findByUrl(String url);
}
