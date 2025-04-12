package com.example.demo.service;

import com.example.demo.data.EducationRequest;
import com.example.demo.data.ResumeRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeDataMapper {

    public ResumeRequest mapToResumeRequest(String pdfContent) {
        ResumeRequest resumeRequest = new ResumeRequest();
        resumeRequest.setName(extractName(pdfContent));
        resumeRequest.setEmail(extractEmail(pdfContent));
        resumeRequest.setContactNumber(extractContactNumber(pdfContent));
        resumeRequest.setEducationRequests(extractEducationRequests(pdfContent));
        return resumeRequest;
    }

    private String extractName(String pdfContent) {
        // Match and record the full name, preserving internal whitespace
        Pattern pattern = Pattern.compile("^([\\w'’]+(?:\\s[\\w'’]+)*)");
        Matcher matcher = pattern.matcher(pdfContent);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }


    private String extractEmail(String pdfContent) {
        Pattern pattern = Pattern.compile("\\s*([\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6})");
        Matcher matcher = pattern.matcher(pdfContent);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    private long extractContactNumber(String pdfContent) {
        Pattern pattern = Pattern.compile("(\\+91\\s?)?(\\d{10})");
        Matcher matcher = pattern.matcher(pdfContent);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(2).trim());
        }
        return 0;
    }

    private List<EducationRequest> extractEducationRequests(String pdfContent) {
        List<EducationRequest> educationRequests = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?i)Education\\s*(.*)", Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(pdfContent);
        if (matcher.find()) {
            String[] lines = matcher.group(1).split("\\n");
            for (String line : lines) {
                String[] educationDetails = line.split("\\|");
                boolean valid = false;
                EducationRequest educationRequest = new EducationRequest();
                for (String detail : educationDetails) {
                    if (containsGraduationYear(detail)) {
                        valid = true;
                    }
                    if (!valid) {
                        continue; // handling my specific case... elements before graduation year are not relevant
                    }
                    String[] parts = detail.split(" ");
                    for (String part : parts) {
                        part = part.replaceAll("[^a-zA-Z0-9']+$", "").trim();
                        if (part.isEmpty()) {
                            continue;
                        }
                        if (isMonthName(part) || part.equalsIgnoreCase("GPA") || part.length() == 6) { // ignore PIN codes
                            continue;
                        }
                        if (part.matches("\\d{4}")) {
                            educationRequest.setGraduationYear(Integer.parseInt(part));
                        } else if (part.matches("\\d+\\.\\d+")) { // Assuming GPA or percentage is a decimal number
                            double value = Double.parseDouble(part);
                            if (value <= 10) {
                                educationRequest.setGrade(convertGpaToGrade(value));
                            } else {
                                educationRequest.setGrade(convertPercentageToGrade(value));
                            }
                        } else if (educationRequest.getCollegeName() == null) {
                            educationRequest.setCollegeName(part);
                        } else if (educationRequest.getCity() == null) {
                            educationRequest.setCity(part);
                        }
                    }
                }
                educationRequests.add(educationRequest);
            }
        }
        return educationRequests;
    }

    private String convertGpaToGrade(double gpa) {
        if (gpa >= 4.0) {
            return "A";
        } else if (gpa >= 3.0) {
            return "B";
        } else if (gpa >= 2.0) {
            return "C";
        } else if (gpa >= 1.0) {
            return "D";
        } else {
            return "F";
        }
    }

    private String convertPercentageToGrade(double percentage) {
        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    private boolean containsGraduationYear(String pdfContent) {
        Pattern pattern = Pattern.compile("\\b\\d{4}\\b");
        Matcher matcher = pattern.matcher(pdfContent);
        return matcher.find();
    }

    private boolean isMonthName(String part) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            if (part.equalsIgnoreCase(month)) {
                return true;
            }
        }
        return false;
    }
}