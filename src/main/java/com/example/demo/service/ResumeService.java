package com.example.demo.service;

import com.example.demo.data.Education;
import com.example.demo.data.Resume;

import java.util.List;

public interface ResumeService {
    String addResume(Resume resume);

    Resume getResume(String userId);

    int addEducation(Education education);

    List<Education> getResumeEducations(String userId);

    Education getEducation(int id);

    List<Resume> getAllResumes();

    List<Education> getAllEducations();
}
