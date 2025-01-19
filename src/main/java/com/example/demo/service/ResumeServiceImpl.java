package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.data.Education;
import com.example.demo.data.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private EducationDao educationDao;

    @Override
    public String addResume(Resume resume) {
        List<Education> educationIds = resume.getEducation().parallelStream().toList();
        educationIds.forEach(this::addEducation);
        return resumeDao.save(resume).getUserId();
    }

    @Override
    public Resume getResume(String userId) {
        return resumeDao.findById(userId).orElseThrow();
    }

    @Override
    public int addEducation(Education education) {
        return educationDao.save(education).getId();
    }

    @Override
    public List<Education> getResumeEducations(String userId) {
        return getResume(userId).getEducation();
    }

    @Override
    public Education getEducation(int id) {
        return educationDao.findById(id).orElseThrow();
    }

    @Override
    public List<Resume> getAllResumes() {
        return (List<Resume>) resumeDao.findAll();
    }

    @Override
    public List<Education> getAllEducations() {
        return (List<Education>) educationDao.findAll();
    }
}
