package com.example.demo.dao;

import com.example.demo.data.Education;
import com.example.demo.data.Resume;
import com.example.demo.data.ResumeEducationMapping;
import org.springframework.data.repository.CrudRepository;

public interface ResumeEducationMappingDao extends CrudRepository<ResumeEducationMapping, String> {
    ResumeEducationMapping getResumeMapping(Resume resume, Education education);
}
