package com.kushal.backend.services;

import com.kushal.backend.constants.Constant;
import com.kushal.backend.entities.Image;
import com.kushal.backend.exceptions.ImageFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Override
    public Image uploadImage(String path, MultipartFile image) throws IOException, ImageFormatException {
        if (!(Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE) || Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE))) {
            throw new ImageFormatException("Provided file is not an image!");
        }
        long timeStamp = Constant.getTimeStamp();
        //Name of the image.
        String originalName = image.getOriginalFilename();
        assert originalName != null;
        String changedName = originalName.replace(" ", "_").toLowerCase().substring(0, originalName.lastIndexOf("."));
        String usableName = changedName + "-" + timeStamp + originalName.substring(originalName.lastIndexOf("."));
        log.info("reached usableName state " + usableName);


        //Full Path where the image will be saved
        String fullPath = path + usableName;

        if(new File(fullPath).exists()) {
            throw new FileAlreadyExistsException(usableName, path, "Provided image already exists.");
        }

        //Creating folder if not present
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
            log.info("folder created if not exist");
        }

        //Copying files to the fullPath
        Files.copy(image.getInputStream(), Paths.get(fullPath));
        log.info("copied image to the " + fullPath);
        Image uploadedImage = new Image(path, usableName, image.getContentType());
        log.info("returned image " + uploadedImage);
        return uploadedImage;
    }

    @Override
    public InputStream getInputStreamUsingPath(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + fileName;
        InputStream stream = new FileInputStream(fullPath);
        return stream;
    }

    @Override
    public void deleteImageUsingPath(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + fileName;
        File file = new File(path);
        if(file.exists()) {
            File f = new File(file, fileName);
            f.delete();
            return;
        }
        throw new FileNotFoundException("File with name " + fileName + " not found in the database!");
    }
}
