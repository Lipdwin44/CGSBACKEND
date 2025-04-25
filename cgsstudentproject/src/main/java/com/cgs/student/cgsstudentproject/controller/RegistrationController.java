package com.cgs.student.cgsstudentproject.controller;


import com.cgs.student.cgsstudentproject.Repo.student_repo;
import com.cgs.student.cgsstudentproject.models.AuthRequest;
import com.cgs.student.cgsstudentproject.models.studentdetails;
import com.cgs.student.cgsstudentproject.services.JWTService;
import com.cgs.student.cgsstudentproject.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.h2.server.web.PageParser.escapeHtml;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class RegistrationController {



@Autowired
private JWTService jwtService;

    @Autowired
    private student_repo student_repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SendEmailService sendEmailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody studentdetails studentRequest) throws Exception {
        String first_name = studentRequest.getFirst_name();
        String last_name = studentRequest.getLast_name();
        String email = studentRequest.getEmail();
        String contact_no = studentRequest.getContact_no();
        String domain = studentRequest.getDomain();

        String username = RegistrationUtil.generateUsername(first_name, last_name);
        String rawPassword = RegistrationUtil.generatePassword(first_name, last_name);
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        studentdetails student = new studentdetails();
        student.setStudentname(username);
        student.setPassword(encryptedPassword);
        student.setEmail(email);
        student.setContact_no(contact_no);
        student.setFirst_name(first_name);
        student.setLast_name(last_name);
        student.setDomain(domain);
        student_repo.save(student);

        String subject = "Registration Confirmation -CGS Pvt. Ltd.";
        String message = "<html><body>" +
                "<p>Dear " + escapeHtml(first_name) + ",</p>" +
                "<p>We are pleased to inform you that your registration with Sygnus TechLabs Pvt. Ltd. has been successfully completed. Below are your account details:</p>" +
                "<p><strong>Username:</strong> " + escapeHtml(username) + "<br>" +
                "<strong>Password:</strong> " + escapeHtml(rawPassword) + "</p>" +
                "<p>Please change your password upon first login.</p>" +
                "<p>Regards,<br>Sygnus TechLabs Team</p>" +
                "</body></html>";

        boolean emailSent = sendEmailService.sendEmailToRecipient(first_name, subject, message);

        if (emailSent) {
            String responseMessage = "Registration successful! Username: " + username + ", Password: " + rawPassword;
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.status(500).body("Registration successful, but failed to send the confirmation email.");
        }
    }


    /*@PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest loginRequest) {
        String username = loginRequest.getStudentname();
        String password = loginRequest.getPassword();
        Optional<studentdetails> student = student_repo.findByStudentname(username);

        if (student.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
        }

        if (!passwordEncoder.matches(password, student.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }

        String role = student.get().getRole(); // Assuming getRole() exists in studentdetails model

        return ResponseEntity.ok("Login successful! Welcome, " + student.get().getFirst_name() + ". Role: " + role);
    }*/




    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody AuthRequest loginRequest) {
        String username = loginRequest.getStudentname();
        String password = loginRequest.getPassword();
        Optional<studentdetails> student = student_repo.findByStudentname(username);

        if (student.isEmpty() || !passwordEncoder.matches(password, student.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials."));
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("firstName", student.get().getFirst_name());
        response.put("role", student.get().getRole());
        response.put("token", jwtService.generateToken(username));

        return ResponseEntity.ok(response);
    }





}