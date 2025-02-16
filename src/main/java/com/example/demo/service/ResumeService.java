package com.example.demo.service;

import com.example.demo.data.Education;
import com.example.demo.data.EducationRequest;
import com.example.demo.data.Resume;
import com.example.demo.data.ResumeEducationMapping;
import com.example.demo.data.ResumeRequest;

import java.util.List;

public interface ResumeService {
    String addResume(ResumeRequest resume);

    Resume getResume(String userId);

    String addEducation(EducationRequest education);

    void addResumeEducationMapping(ResumeEducationMapping mapping);

    Education getEducation(int id);

    List<Resume> getAllResumes();

    List<Education> getAllEducations();
}
