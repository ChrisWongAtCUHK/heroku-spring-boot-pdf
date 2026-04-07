package com.heroku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@SpringBootApplication
@Controller
public class MainApplication {
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", "This is a test");
        return "index";
    }

    @GetMapping("/view-pdf")
    public ResponseEntity<Resource> viewPdf(@RequestParam(required = true) String name) {
        Resource pdf = resourceLoader.getResource("file:./pdf/" + name);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"name\"")
                .body(pdf);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
