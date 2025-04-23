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
            URL imageUrl = new URL("https://img1.wsimg.com/isteam/ip/d7e802a8-158d-42ab-86a7-ff9335df0285/SYGNUS%20LOGO%20test.jpg/:/rs=w:145,h:49,cg:true,m/cr=w:145,h:49/qt=q:95");
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
