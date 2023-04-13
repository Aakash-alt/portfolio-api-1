package com.kushal.backend.services;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Image;
import com.kushal.backend.entities.Project;
import com.kushal.backend.entities.Socials;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import com.kushal.backend.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Project> getListOfProducts() throws NotFoundException {
        List<Project> projects = repository.findAll();
        if (projects.size() == 0) {
            throw new NotFoundException("No projects found!");
        }
        return projects;
    }

    @Override
    public Project postProduct(Project project, List<MultipartFile> images) throws IOException, ImageFormatException {
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile image : images) {
            Image uploadedImage = imageService.uploadImage(Constant.IMAGE_BASE_URL, image);
            imageList.add(uploadedImage);
        }
        project.setImages(imageList);
        log.info(project.toString());
        Project saved = repository.save(project);
        log.info(saved.toString());
        return saved;
    }

    @Override
    public List<Project> getProductByTitle(String title) throws NotFoundException {
        Optional<List<Project>> optionalProject = repository.findByTitle(title);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        }
        throw new NotFoundException("No project found with title " + title);
    }

    @Override
    public List<Project> getProductByUrl(String url) throws NotFoundException {
        Optional<Project> optionalProject = repository.findByUrl(url);
        if (optionalProject.isPresent()) {
            return List.of(optionalProject.get());
        }
        throw new NotFoundException("No project found with url " + url);
    }

    @Override
    public Project updateProduct(
            String projectId,
            String title,
            String serviceProvided,
            String author,
            String client,
            String startDate,
            String endDate,
            String url,
            String instagram,
            String facebook,
            String linkedin,
            String youtube,
            List<MultipartFile> images
    ) throws NotFoundException, IOException, ImageFormatException {
        Optional<Project> maybeProject = repository.findById(Long.valueOf(projectId));
        if (maybeProject.isPresent()) {
            Project project = maybeProject.get();
            if (title != null && !title.equals("")) {
                project.setTitle(title);
            }
            if (serviceProvided != null && !serviceProvided.equals("")) {
                project.setService(serviceProvided);
            }
            if (author != null && !author.equals("")) {
                project.setAuthor(author);
            }
            if (client != null && !client.equals("")) {
                project.setClient(client);
            }
            if (startDate != null && !startDate.equals("")) {
                project.setStartDate(startDate);
            }
            if (endDate != null && !endDate.equals("")) {
                project.setEndDate(endDate);
            }
            if (url != null && !url.equals("")) {
                project.setUrl(url);
            }
            if (
                    instagram != null && !instagram.equals("") ||
                            facebook != null && !facebook.equals("") ||
                            linkedin != null && !linkedin.equals("") ||
                            youtube != null && !youtube.equals("")
            ) {

                Socials social = project.getSocials();
                if (social == null) {
                    social = new Socials();
                }

                if (instagram != null && !instagram.equals("")) {
                    social.setInstagram(instagram);
                }
                if (facebook != null && !facebook.equals("")) {
                    social.setFacebook(facebook);
                }
                if (linkedin != null && !linkedin.equals("")) {
                    social.setLinkedin(linkedin);
                }
                if (youtube != null && !youtube.equals("")) {
                    social.setYoutube(youtube);
                }

                project.setSocials(social);

            }
            if (images != null && images.size() > 0) {
                for (Image image : project.getImages()) {
                    imageService.deleteImageUsingPath(image.getPath(), image.getName());
                }
                List<Image> imageList = new ArrayList<>();
                for (MultipartFile image : images) {
                    Image uploadedImage = imageService.uploadImage(Constant.IMAGE_BASE_URL, image);
                    imageList.add(uploadedImage);
                }
                project.setImages(imageList);
            }
            return repository.save(project);
        }
        throw new NotFoundException("No project found by the id " + projectId);
    }

    @Override
    public Project deleteProject(String projectId) throws NotFoundException, FileNotFoundException {
        Optional<Project> maybeProject = repository.findById(Long.valueOf(projectId));
        if (maybeProject.isPresent()) {
            Project project = maybeProject.get();
            for (Image image : project.getImages()) {
                imageService.deleteImageUsingPath(image.getPath(), image.getName());
            }
            repository.delete(project);
            return project;
        }
        throw new NotFoundException("No project found with id " + projectId);
    }

}
