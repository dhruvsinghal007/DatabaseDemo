package com.example.demo;

import com.example.demo.dao.EducationDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.data.Education;
import com.example.demo.data.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private EducationDao educationDao;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("resume")
    public List<Resume> getAllResumes() {
        return (List<Resume>) resumeDao.findAll();
    }

    @GetMapping("education")
    public List<Education> getAllEducations() {
        return (List<Education>) educationDao.findAll();
    }

    @GetMapping("resume/{userid}")
    public Resume getResume(@PathVariable String userid) {
        return resumeDao.findById(userid).orElse(null);
    }

    @PostMapping("resume")
    public Resume addResume(@RequestBody Resume resume) {
        return resumeDao.save(resume);
    }

    @PostMapping("education")
    public Education addEducation(@RequestBody Education education) {
        return educationDao.save(education);
    }

    @GetMapping("resume/{userid}/education")
    public List<Education> findEducationsOfUser(@PathVariable String userid) {
        return resumeDao.findById(userid).orElseThrow().getEducation();
    }
}
