package com.heroku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@SpringBootApplication
@Controller
public class MainApplication {
    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", "This is a test");
        return "index";
    }

    @GetMapping("/upload")
    public String upload(Map<String, Object> model) {
        return "upload";
    }

    @GetMapping("/view-pdf")
    public ResponseEntity<Resource> viewPdf() {
        Resource pdf = new ClassPathResource("static/sample.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"sample.pdf\"")
                .body(pdf);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
