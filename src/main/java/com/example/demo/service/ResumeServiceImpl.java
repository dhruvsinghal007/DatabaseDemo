package com.example.demo.service;

import com.example.demo.dao.EducationDao;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dao.ResumeEducationMappingDao;
import com.example.demo.data.Education;
import com.example.demo.data.EducationRequest;
import com.example.demo.data.Resume;
import com.example.demo.data.ResumeEducationMapping;
import com.example.demo.data.ResumeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {
    private static final Logger logger = LoggerFactory.getLogger(ResumeServiceImpl.class);

    @Autowired
    private ResumeDao resumeDao;

    @Autowired
    private EducationDao educationDao;

    @Autowired
    private ResumeEducationMappingDao resumeEducationMappingDao;

    private String generateId(String name) throws IllegalArgumentException {
        logger.info("Generating unique id for {}", name);
        String uniqueId = UUID.randomUUID().toString().substring(0, 4); // keep it simple for now
        logger.debug("Generated unique id: {}", uniqueId);
        return name.toLowerCase().replaceAll("\\s+", "") + uniqueId;
    }

    // TODO add swagger documentation
    // TODO use github-copilot to upload pdf and parse (after the above steps are done)
    @Override
    public String addResume(ResumeRequest resumeRequest) {
        Resume resume = getResume(resumeRequest.getName());
        logger.info("Adding resume for {}", resumeRequest.getName());
        if (resume != null) {
            logger.debug("Resume already exists for {}. Updating the information...", resumeRequest.getName());
        } else {
            resume = new Resume();
            resume.setName(resumeRequest.getName());
            resume.setId(generateId(resumeRequest.getName()));
        }
        resume.setContactNumber(resumeRequest.getContactNumber());
        resume.setEmail(resumeRequest.getEmail());
        resumeDao.save(resume);
        for (EducationRequest educationRequest : resumeRequest.getEducationRequests()) {
            Education education = getEducationFromRequest(educationRequest);
            educationDao.save(education);
            ResumeEducationMapping mapping = getResumeEducationMapping(resumeRequest, educationRequest, resume, education);
            addResumeEducationMapping(mapping);
        }
        return resume.getId();
    }

    private ResumeEducationMapping getResumeEducationMapping(ResumeRequest resumeRequest, EducationRequest educationRequest, Resume resume,
                                                             Education education) {
        ResumeEducationMapping mapping = resumeEducationMappingDao.getResumeMapping(resume, education);
        if (mapping != null) {
            logger.debug("Mapping already exists for {} and {}. Updating the information...", resume.getName(), education.getCollegeName());
        } else {
            mapping = new ResumeEducationMapping();
            mapping.setId(generateId(resumeRequest.getName() + education.getCollegeName()));
            mapping.setResume(resume);
            mapping.setEducation(education);
        }
        mapping.setGrade(educationRequest.getGrade());
        mapping.setGraduationYear(educationRequest.getGraduationYear());
        return mapping;
    }

    @Override
    public Resume getResume(String name) {
        logger.info("Fetching resume for {}", name);
        return resumeDao.getResume(name);
    }

    @Override
    public String addEducation(EducationRequest educationRequest) {
        logger.info("Adding education for {}", educationRequest);
        Education education = getEducationFromRequest(educationRequest);
        return educationDao.save(education).getId();
    }

    private Education getEducationFromRequest(EducationRequest educationRequest) {
        Education education = educationDao.getEducation(educationRequest.getCollegeName());
        if (education != null) {
            logger.debug("Education already exists for {}. Updating the information...", educationRequest.getCollegeName());
        } else {
            education = new Education();
            education.setCollegeName(educationRequest.getCollegeName());
            education.setId(generateId(education.getCollegeName()));
        }
        education.setCity(educationRequest.getCity());
        return education;
    }

    @Override
    public void addResumeEducationMapping(ResumeEducationMapping mapping) {
        resumeEducationMappingDao.save(mapping);
    }

    @Override
    public Education getEducation(String name) {
        logger.info("Fetching education for name {}", name);
        return educationDao.getEducation(name);
    }

    @Override
    public List<Resume> getAllResumes() {
        logger.info("Fetching all resumes");
        return (List<Resume>) resumeDao.findAll();
    }

    @Override
    public List<Education> getAllEducations() {
        logger.info("Fetching all educations");
        return (List<Education>) educationDao.findAll();
    }
}
