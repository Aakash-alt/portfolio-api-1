package com.kushal.backend.services;

import com.kushal.backend.entities.Image;
import com.kushal.backend.exceptions.ImageFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    Image uploadImage(String path, MultipartFile image) throws IOException, ImageFormatException;
    InputStream getInputStreamUsingPath(String path, String fileName) throws FileNotFoundException;

    void deleteImageUsingPath(String path, String fileName) throws FileNotFoundException;
}
