package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Resume {
    @Id
    private String id;
    private String name;
    private String email;
    private Long contactNumber;

    @OneToMany(mappedBy = "resume")
    private List<ResumeEducationMapping> educationMappings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<ResumeEducationMapping> getEducationMappings() {
        return educationMappings;
    }

    public void setEducationMappings(List<ResumeEducationMapping> educationMappings) {
        this.educationMappings = educationMappings;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", educations=" + educationMappings +
                '}';
    }
}
