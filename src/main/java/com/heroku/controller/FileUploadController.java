package com.heroku.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "File is empty";
        }
        // Access file properties
        String fileName = file.getOriginalFilename();
        long size = file.getSize();

        // Save the file or process bytes
        String uploadDir = "./pdf/";
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "Successfully uploaded: " + fileName + "(" + size + ")";
    }
}