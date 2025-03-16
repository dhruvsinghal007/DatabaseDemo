package com.example.demo.dao;

import com.example.demo.data.Education;
import com.example.demo.data.Resume;
import com.example.demo.data.ResumeEducationMapping;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class ResumeEducationMappingDaoImpl extends SimpleJpaRepository<ResumeEducationMapping, String> implements ResumeEducationMappingDao {
    @PersistenceContext
    private final EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(ResumeEducationMappingDaoImpl.class);

    public ResumeEducationMappingDaoImpl(EntityManager entityManager) {
        super(ResumeEducationMapping.class, entityManager);
        this.em = entityManager;
    }

    @Override
    public ResumeEducationMapping getResumeMapping(Resume resume, Education education) {
        try {
            String query = "SELECT rem FROM ResumeEducationMapping rem WHERE rem.resume = :resume AND rem.education = :education";
            return em.createQuery(query, ResumeEducationMapping.class)
                    .setParameter("resume", resume)
                    .setParameter("education", education)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error("Error fetching resume education mapping for {} and {}", resume, education);
            return null;
        }
    }
}
