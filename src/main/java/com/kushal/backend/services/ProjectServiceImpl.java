package com.kushal.backend.services;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Image;
import com.kushal.backend.entities.Project;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import com.kushal.backend.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public List<Project> getListOfProducts() {
        return repository.findAll();
    }

    @Override
    public Project postProduct(Project project, List<MultipartFile> images) throws IOException, ImageFormatException {
        List<Image> imageList = new ArrayList<>();
        for(MultipartFile image: images) {
            Image uploadedImage = imageService.uploadImage(Constant.IMAGE_BASE_URL, image);
//            uploadedImage.setProject(project);
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
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        }
        throw new NotFoundException("No project found with title " + title);
    }

    @Override
    public List<Project> getProductByUrl(String url) throws NotFoundException {
        Optional<List<Project>> optionalProject = repository.findByUrl(url);
        if(optionalProject.isPresent()) {
            return optionalProject.get();
        }
        throw new NotFoundException("No project found with url " + url);
    }

}
