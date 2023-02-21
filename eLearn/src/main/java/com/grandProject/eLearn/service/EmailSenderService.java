package com.grandProject.eLearn.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmailFromApp(String toEmail,String name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("musikacademytest@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Successful registration");
        message.setText("Greetings "+ name + ", "+ "\n Your registration on MusikAcademy was successful !");

        mailSender.send(message);

    }
    public void sendNewsletterEmail(String toEmail,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Newsletter");
        message.setText(content);

        mailSender.send(message);
    }

    public void sendMentorApplication(String fromEmail,String applicationMessage){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("musikacademytest@gmail.com");
        message.setSubject("Mentor application "+ fromEmail);
        message.setText(applicationMessage);
        mailSender.send(message);

    }
}
