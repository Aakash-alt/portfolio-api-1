package com.kushal.backend.controllers;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Project;
import com.kushal.backend.entities.Socials;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import com.kushal.backend.response.DeleteResponse;
import com.kushal.backend.response.PostResponse;
import com.kushal.backend.response.UpdateResponse;
import com.kushal.backend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
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
            @RequestParam("service") String serviceProvided,
            @RequestParam("author") String author,
            @RequestParam("client") String client,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("url") String url,
            @RequestParam(value = "client_instagram", required = false) String instagram,
            @RequestParam(value = "client_linkedin", required = false) String linkedin,
            @RequestParam(value = "client_facebook", required = false) String facebook,
            @RequestParam(value = "client_youtube", required = false) String youtube,
            @RequestParam("image") List<MultipartFile> images
    ) throws IOException, ImageFormatException {
        Project postedProject = service.postProduct(
                new Project(
                        title,
                        serviceProvided,
                        author,
                        client,
                        startDate,
                        endDate,
                        url,
                        new Socials(
                                0L,
                                instagram,
                                linkedin,
                                facebook,
                                youtube
                        )
                ),
                images
        );
        return new PostResponse<>(
                HttpStatus.OK,
                postedProject,
                postedProject.getTitle() + " posted successfully!",
                Constant.getTimeStamp()
        );
    }

    @PutMapping("/update")
    public UpdateResponse<Project> updateProjectUsingId(
            @RequestParam("projectId") String projectId,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "service", required = false) String serviceProvided,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "client", required = false) String client,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "client_instagram", required = false) String instagram,
            @RequestParam(value = "client_facebook", required = false) String facebook,
            @RequestParam(value = "client_linkedin", required = false) String linkedin,
            @RequestParam(value = "client_youtube", required = false) String youtube,
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws NotFoundException, IOException, ImageFormatException {
        Project updatedProject = service.updateProduct(
                projectId,
                title,
                serviceProvided,
                author,
                client,
                startDate,
                endDate,
                url,
                instagram,
                facebook,
                linkedin,
                youtube,
                images
        );
        return new UpdateResponse<>(
                HttpStatus.OK,
                projectId,
                updatedProject,
                "Project with id " + projectId + " updated successfully!",
                Constant.getTimeStamp()
        );
    }

    @DeleteMapping("/delete")
    public DeleteResponse<Project> deleteProjectUsingId(
            @RequestParam("projectId") String projectId
    ) throws FileNotFoundException, NotFoundException {
        Project deletedProject = service.deleteProject(projectId);
        return new DeleteResponse<>(
                HttpStatus.OK,
                deletedProject,
                "Project with id " + projectId + " deleted successfully!",
                Constant.getTimeStamp()
        );
    }

}

