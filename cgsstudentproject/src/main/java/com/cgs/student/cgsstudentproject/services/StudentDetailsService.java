package com.cgs.student.cgsstudentproject.services;


import com.cgs.student.cgsstudentproject.Repo.student_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsService {

    @Autowired
    private student_repo student_repo;


    public int getTotalStudents() {
        return student_repo.countAllStudents();
    }
}
