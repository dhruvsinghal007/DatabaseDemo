package com.example.demo.dao;

import com.example.demo.data.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationDao extends CrudRepository<Education, String> {
    Education getEducation(String collegeName);
}
