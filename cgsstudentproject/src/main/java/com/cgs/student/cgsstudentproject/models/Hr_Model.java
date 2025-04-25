package com.cgs.student.cgsstudentproject.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Hr_table")
public class Hr_Model {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getHr_name() {
        return Hr_name;
    }

    public void setHr_name(String hr_name) {
        Hr_name = hr_name;
    }

    public String getHr_email() {
        return Hr_email;
    }

    public void setHr_email(String hr_email) {
        Hr_email = hr_email;
    }

    public String getHr_number() {
        return Hr_number;
    }

    public void setHr_number(String hr_number) {
        Hr_number = hr_number;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    @Override
    public String toString() {
        return "Hr_Model{" +
                "id=" + id +
                ", companyname='" + companyname + '\'' +
                ", Hr_name='" + Hr_name + '\'' +
                ", Hr_email='" + Hr_email + '\'' +
                ", Hr_number='" + Hr_number + '\'' +
                ", job_desc='" + job_desc + '\'' +
                '}';
    }

    @Id
    public int id;
    public String companyname;
    public String Hr_name;
    public String Hr_email;
    public String Hr_number;
    public String job_desc;
}
