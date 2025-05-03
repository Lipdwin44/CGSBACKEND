package com.cgs.student.cgsstudentproject.services;


import jakarta.activation.URLDataSource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmailToRecipient(String toEmail, String subject, String message) {
        try {
            // Create the MIME message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Set email properties
            helper.setFrom("support@sygnustechlabs.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(message, true);

            // Add inline image
            URL imageUrl = new URL("https://media.licdn.com/dms/image/v2/D563DAQHzHjhRAyjGlA/image-scale_191_1128/image-scale_191_1128/0/1679394811361/codesightglobalsolutions_cover?e=2147483647&v=beta&t=Xy98IeGQ-szM9JjvuDgbje2cnBaCGJil1R51YLrRxg4");
            URLDataSource urlDataSource = new URLDataSource(imageUrl);

            helper.addInline("image1", urlDataSource);

            // Send the email
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Email sending failed: " + e.getMessage());
            return false;
        }
    }
}
