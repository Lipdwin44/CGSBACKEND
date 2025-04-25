package com.cgs.student.cgsstudentproject.Repo;

import com.cgs.student.cgsstudentproject.models.studentdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface student_repo extends JpaRepository<studentdetails, Integer> {


    Optional<studentdetails> findByStudentname(String studentname);

    @Query("SELECT COUNT(s) FROM studentdetails s")
    int countAllStudents();
}


