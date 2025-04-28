package com.cgs.student.cgsstudentproject.controller;

import com.cgs.student.cgsstudentproject.Repo.student_repo;
import com.cgs.student.cgsstudentproject.models.StudentSimpleDTO;
import com.cgs.student.cgsstudentproject.models.studentdetails;
import com.cgs.student.cgsstudentproject.services.StudentDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000") // ✅ Allow React frontend access
@RestController
@RequestMapping("/api/student")
public class Studentcontroller {

    @Autowired
    private student_repo studentRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private StudentDetailsService studentService;
    @PostMapping("/register")
    public ResponseEntity<String> registerStudent(@RequestBody studentdetails st) {
        if (st.getStudentname() == null ||
                st.getDomain() == null ||
                st.getEmail() == null ||
                st.getContact_no() == null ||
                st.getPassword() == null) {
            return ResponseEntity.badRequest().body("All fields including password are required.");
        }

        // Encode password before saving
        st.setPassword(encoder.encode(st.getPassword()));
        studentRepository.save(st);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ Get all students
    @GetMapping("/students")
    public ResponseEntity<List<studentdetails>> getAllStudents() {
        List<studentdetails> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // ✅ Get CSRF token (optional for frontend)
    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }




    @GetMapping("/count")
    public int getTotalStudents() {
        return studentService.getTotalStudents();
    }


    @GetMapping("/domain/{domain}")
    public List<StudentSimpleDTO> getStudentsByDomain(@PathVariable String domain) {
        return studentService.getStudentsByDomain(domain);
    }
}



