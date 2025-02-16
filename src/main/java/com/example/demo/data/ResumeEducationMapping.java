package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resume_education_mapping")
public class ResumeEducationMapping {
    @Id
    private String id;
    private int graduationYear;
    private String grade;

    @ManyToOne
    private Resume resume;

    @ManyToOne
    private Education education;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "ResumeEducationMapping{" +
                "id='" + id + '\'' +
                ", graduationYear=" + graduationYear +
                ", grade='" + grade + '\'' +
                ", resume=" + resume +
                ", education=" + education +
                '}';
    }
}
