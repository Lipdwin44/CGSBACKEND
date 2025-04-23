package com.cgs.student.cgsstudentproject.services;

import com.cgs.student.cgsstudentproject.Repo.student_repo;
import com.cgs.student.cgsstudentproject.config.Userprinciples;
import com.cgs.student.cgsstudentproject.models.studentdetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserdetails implements UserDetailsService {

    @Autowired
    private student_repo student_repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        studentdetails student = student_repo.findByStudentname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Student not found"));
        return new Userprinciples(student);
    }
}
