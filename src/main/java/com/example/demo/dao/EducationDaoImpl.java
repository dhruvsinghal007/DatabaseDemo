package com.example.demo.dao;

import com.example.demo.data.Education;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EducationDaoImpl extends SimpleJpaRepository<Education, String> implements EducationDao {
    @PersistenceContext
    private final EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(EducationDaoImpl.class);

    public EducationDaoImpl(EntityManager entityManager) {
        super(Education.class, entityManager);
        this.em = entityManager;
    }

    @Override
    public Education getEducation(String collegeName) {
        try {
            String query = "SELECT e FROM Education e WHERE e.collegeName = :collegeName";
            logger.debug("Query: {}", query);
            return em.createQuery(query, Education.class)
                    .setParameter("collegeName", collegeName)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error("Error fetching education for {}. Error Message: {}", collegeName, e.getMessage());
            return null;
        }
    }
}
