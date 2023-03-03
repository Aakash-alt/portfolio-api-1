package com.kushal.backend.services;

import com.kushal.backend.entities.Project;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProjectService {

    List<Project> getListOfProducts();

    Project postProduct(Project project, List<MultipartFile> images) throws IOException, ImageFormatException;

    List<Project> getProductByTitle(String title) throws NotFoundException;

    List<Project> getProductByUrl(String url) throws NotFoundException;
}
