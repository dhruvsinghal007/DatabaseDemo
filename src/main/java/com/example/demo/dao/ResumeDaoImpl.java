package com.example.demo.dao;

import com.example.demo.data.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ResumeDaoImpl extends SimpleJpaRepository<Resume, String> implements ResumeDao {
    @PersistenceContext
    private final EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(ResumeDaoImpl.class);

    public ResumeDaoImpl(EntityManager entityManager) {
        super(Resume.class, entityManager);
        this.em = entityManager;
    }

    @Override
    public Resume getResume(String name) {
        try {
            String query = "SELECT r FROM Resume r WHERE r.name = :name";
            logger.debug("Query: {}", query);
            return em.createQuery("SELECT r FROM Resume r WHERE r.name = :name", Resume.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error("Error fetching resume for {}. Error Message: {}", name, e.getMessage());
            return null;
        }
    }
}
