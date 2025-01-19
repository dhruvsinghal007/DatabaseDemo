package com.example.demo;

import com.example.demo.data.Education;
import com.example.demo.data.Resume;
import com.example.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("resume/{userid}")
    public Resume getResume(@PathVariable String userid) {
        return service.getResume(userid);
    }

    @PostMapping("resume")
    public String addResume(@RequestBody Resume resume) {
        return service.addResume(resume);
    }

    @PostMapping("education")
    public int addEducation(@RequestBody Education education) {
        return service.addEducation(education);
    }

    @GetMapping("resume/{userid}/education")
    public List<Education> findEducationsOfUser(@PathVariable String userid) {
        return service.getResumeEducations(userid);
    }

    @GetMapping("education/{id}")
    public Education getEducation(@PathVariable int id) {
        return service.getEducation(id);
    }
}
