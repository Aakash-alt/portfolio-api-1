package com.kushal.backend.controllers;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Project;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import com.kushal.backend.response.PostResponse;
import com.kushal.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public List<Project> listProjects(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "url", required = false) String url
    ) throws NotFoundException {
        if (title == null && url == null) {
            return service.getListOfProducts();
        }
        if (title != null) {
            return service.getProductByTitle(title);
        }
        return service.getProductByUrl(url);
    }

    @PostMapping
    public PostResponse<Project> postProject(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("author") String author,
            @RequestParam("client") String client,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("url") String url,
            @RequestParam("image") List<MultipartFile> images
    ) throws IOException, ImageFormatException {
        Project postedProject = service.postProduct(
                new Project(title, description, author, client, startDate, endDate, url),
                images
        );
        return new PostResponse<>(
                HttpStatus.OK,
                postedProject,
                postedProject.getTitle() + " posted successfully!",
                Constant.getTimeStamp()
        );
    }


}

