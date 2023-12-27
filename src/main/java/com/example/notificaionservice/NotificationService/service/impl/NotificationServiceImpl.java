package com.example.notificaionservice.NotificationService.service.impl;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.repository.NotificationRepository;
import com.example.notificaionservice.NotificationService.repository.NotificationTypeRepository;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    NotificationTypeRepository notificationTypeRepository;

    @Autowired
    private JavaMailSender mailSender;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }


    @Override
    public void registration(NotificationCreateDto notificationCreateDto) {
        NotificationType notificationType = new NotificationType("Activation");
        notificationTypeRepository.save(notificationType);
        Notification notificaton = new Notification(notificationCreateDto.getFirstName(), notificationCreateDto.getLastName()
                , "", notificationType, notificationCreateDto.getUsername(), notificationCreateDto.getEmail());

        String email = "Hello " + notificationCreateDto.getFirstName() + " " + notificationCreateDto.getLastName() + ",\n\n" +
                "Welcome to our application. Please activate your account by clicking on the link below.\n\n" +
                notificaton.getLink() + ",\n\n" +
                "Best Regards,\n" +
                "Support team\n" +
                "Fitness Center";
        sendEmail(notificationCreateDto.getEmail(), "Account activation", email);
        notificationRepository.save(notificaton);
    }

    @Override
    public void changePassword(NotificationCreateDto notificationCreateDto) {
        NotificationType notificationType = new NotificationType("Changed password");
        notificationTypeRepository.save(notificationType);
        Notification notificaton = new Notification(notificationCreateDto.getFirstName(), notificationCreateDto.getLastName()
                ,"http://localhost:4200/client-home-page", notificationType, notificationCreateDto.getUsername(), notificationCreateDto.getEmail());

        String email = "Hello " + notificationCreateDto.getFirstName() + " " + notificationCreateDto.getLastName() + ",\n\n" +
                "Your password has been successfully changed.\n\n" +
                "you can return to the site" + notificaton.getLink() + "\n\n" +
                "Best Regards,\n" +
                "Support team\n" +
                "Fitness Center";
        sendEmail(notificationCreateDto.getEmail(), "Changed password", email);
        notificationRepository.save(notificaton);
    }

    public void sendEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        //message.setFrom("arjungautam8877@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("RADIII Jupi");
    }
}
