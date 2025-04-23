package com.cgs.student.cgsstudentproject.controller;

import com.cgs.student.cgsstudentproject.Repo.student_repo;
import com.cgs.student.cgsstudentproject.models.studentdetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class Studentcontroller {
    @Autowired
    private student_repo studentRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody studentdetails st){
        if (st.getStudentname() == null ||
                st.getDomain() == null ||
                st.getEmail() == null ||
                st.getContact_no() == null){
            return ResponseEntity.badRequest().body("All fields are required");
        }
        st.setPassword(encoder.encode(st.getPassword()));
        studentRepository.save(st);
        return ResponseEntity.ok("user registered successfully");
    }
    @GetMapping("/students")
    public ResponseEntity<List<studentdetails>> getAllStudents(){
        List<studentdetails> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

//    @GetMapping("/csrf-token")
//    public CsrfToken csrfToken(HttpServletRequest request) {
//        // Fetch the CSRF token from the request
//        return  (CsrfToken) request.getAttribute("_csrf");
//    }


    @GetMapping("/Csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
