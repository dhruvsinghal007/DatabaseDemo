package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Education {
    @Id
    private String id;
    private String collegeName;
    private String city;

    @OneToMany(mappedBy = "education")
    private List<ResumeEducationMapping> resumeMappings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id='" + id + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
