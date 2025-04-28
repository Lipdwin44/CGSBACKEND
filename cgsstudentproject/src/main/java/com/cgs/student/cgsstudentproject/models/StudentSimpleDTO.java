package com.cgs.student.cgsstudentproject.models;

public class StudentSimpleDTO {
    private String domain;
    private String firstName;
    private String lastName;

    public StudentSimpleDTO(String domain, String firstName, String lastName) {
        this.domain = domain;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and Setters
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

