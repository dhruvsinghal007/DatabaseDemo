package com.example.demo.dao;

import com.example.demo.data.Resume;
import org.springframework.data.repository.CrudRepository;

public interface ResumeDao extends CrudRepository<Resume, String> {
}
