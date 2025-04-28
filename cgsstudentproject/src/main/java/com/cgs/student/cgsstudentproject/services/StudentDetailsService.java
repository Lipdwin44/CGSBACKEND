package com.cgs.student.cgsstudentproject.services;


import com.cgs.student.cgsstudentproject.Repo.student_repo;
import com.cgs.student.cgsstudentproject.models.StudentSimpleDTO;
import com.cgs.student.cgsstudentproject.models.studentdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDetailsService {

    @Autowired
    private student_repo student_repo;


    public int getTotalStudents() {
        return student_repo.countAllStudents();
    }


    public List<StudentSimpleDTO> getStudentsByDomain(String domain) {
        List<studentdetails> students = student_repo.findByDomain(domain);
        return students.stream()
                .map(student -> new StudentSimpleDTO(
                        student.getDomain(),
                        student.getFirst_name(),
                        student.getLast_name()))
                .collect(Collectors.toList());
    }
}
