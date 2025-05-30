package com.cgs.student.cgsstudentproject.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Student_details")
public class studentdetails {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String studentname;
    public String email;
    public String contact_no;
    public String domain;
    public String first_name;
    public String last_name;
    public String residential_address;
    public String permanent_address;
    public boolean is_offer_accept;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private LocalDateTime offerAcceptedDate;

    public LocalDateTime getOfferRejectedDate() {
        return offerRejectedDate;
    }

    public void setOfferRejectedDate(LocalDateTime offerRejectedDate) {
        this.offerRejectedDate = offerRejectedDate;
    }

    public LocalDateTime getOfferAcceptedDate() {
        return offerAcceptedDate;
    }

    public void setOfferAcceptedDate(LocalDateTime offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isIs_offer_accept() {
        return is_offer_accept;
    }

    public void setIs_offer_accept(boolean is_offer_accept) {
        this.is_offer_accept = is_offer_accept;
    }

    private LocalDateTime offerRejectedDate;







    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }



    public String pin_code;

    public String getPincode() {
        return pin_code;
    }

    public void setPincode(String pincode) {
        this.pin_code = pincode;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String password;

    @Override
    public String toString() {
        return "studentdetails{" +
                "id=" + id +
                ", studentname='" + studentname + '\'' +
                ", email='" + email + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", domain='" + domain + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", residential_address='" + residential_address + '\'' +
                ", permanent_address='" + permanent_address + '\'' +
                ", is_offer_accept=" + is_offer_accept +
                ", createdDate=" + createdDate +
                ", offerAcceptedDate=" + offerAcceptedDate +
                ", offerRejectedDate=" + offerRejectedDate +
                ", pin_code='" + pin_code + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}









