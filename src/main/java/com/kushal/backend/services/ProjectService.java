package com.kushal.backend.services;

import com.kushal.backend.entities.Project;
import com.kushal.backend.exceptions.ImageFormatException;
import com.kushal.backend.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProjectService {

    List<Project> getListOfProducts() throws NotFoundException;

    Project postProduct(Project project, List<MultipartFile> images) throws IOException, ImageFormatException;

    List<Project> getProductByTitle(String title) throws NotFoundException;

    List<Project> getProductByUrl(String url) throws NotFoundException;

    Project updateProduct(String projectId, String title, String serviceProvided, String author, String client, String startDate, String endDate, String url, String instagram, String facebook, String linkedin, String youtube, List<MultipartFile> images) throws NotFoundException, IOException, ImageFormatException;

    Project deleteProject(String projectId) throws NotFoundException, FileNotFoundException;
}
