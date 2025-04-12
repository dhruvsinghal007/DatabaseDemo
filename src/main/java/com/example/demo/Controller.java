package com.example.demo;

import com.example.demo.data.Education;
import com.example.demo.data.EducationRequest;
import com.example.demo.data.Resume;
import com.example.demo.data.ResumeRequest;
import com.example.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ResumeService service;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("resume")
    public List<Resume> getAllResumes() {
        return service.getAllResumes();
    }

    @GetMapping("education")
    public List<Education> getAllEducations() {
        return service.getAllEducations();
    }

    @PostMapping("resume")
    public String addResume(@RequestBody ResumeRequest resume) {
        return service.addResume(resume);
    }

    @PostMapping("education")
    public String addEducation(@RequestBody EducationRequest education) {
        return service.addEducation(education);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            String resumeId = service.parseAndSaveResume(file);
            return ResponseEntity.ok("Resume uploaded successfully with ID: " + resumeId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error parsing PDF file: " + e.getMessage());
        }
    }
}
