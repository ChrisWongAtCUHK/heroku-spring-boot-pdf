package com.heroku.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty";
        }
        // Access file properties
        String fileName = file.getOriginalFilename();
        long size = file.getSize();

        // Save the file or process bytes
        // file.transferTo(new File("/path/to/destination/" + fileName))

        return "Successfully uploaded: " + fileName + "(" + size +")";
    }
}