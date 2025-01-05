package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Education {
    @Id
    private Integer id;
    private String collegeName;
    private Integer graduationYear;
    private Double gpa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", collegeName='" + collegeName + '\'' +
                ", graduationYear=" + graduationYear +
                ", gpa=" + gpa +
                '}';
    }
}
