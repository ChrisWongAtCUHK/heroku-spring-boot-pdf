package com.heroku.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.heroku.Constant;

@Controller
public class FileUploadController {
    @GetMapping("/upload")
    public String upload(Map<String, Object> model) {
        return "upload";
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "File is empty";
        }
        // Access file properties
        String fileName = file.getOriginalFilename();

        // Save the file or process bytes
        String uploadDir = "./" + Constant.FOLDER + "/";
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return "redirect:/" + Constant.VIEW_PDF_URL + "?name=" + fileName;
    }
}