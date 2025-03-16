package com.example.demo.data;

import java.util.List;

public class ResumeRequest {
    private String name;
    private String email;
    private Long contactNumber;
    private List<EducationRequest> educationRequests;

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

    public List<EducationRequest> getEducationRequests() {
        return educationRequests;
    }

    public void setEducationRequests(List<EducationRequest> educationRequests) {
        this.educationRequests = educationRequests;
    }

    @Override
    public String toString() {
        return "ResumeRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", educationRequests=" + educationRequests +
                '}';
    }
}
