package com.example.demo.data;

public class EducationRequest {
    private String collegeName;
    private String city;
    private int graduationYear;
    private String grade;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public String toString() {
        return "EducationRequest{" +
                "collegeName='" + collegeName + '\'' +
                ", city='" + city + '\'' +
                ", graduationYear=" + graduationYear +
                ", grade=" + grade +
                '}';
    }
}
