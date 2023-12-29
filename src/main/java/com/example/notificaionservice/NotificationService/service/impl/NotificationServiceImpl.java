package com.example.notificaionservice.NotificationService.service.impl;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import com.example.notificaionservice.NotificationService.mapper.NotificationsMapper;
import com.example.notificaionservice.NotificationService.repository.NotificationRepository;
import com.example.notificaionservice.NotificationService.repository.NotificationTypeRepository;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import io.jsonwebtoken.Claims;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationsMapper notificationsMapper;
    @Autowired
    private JavaMailSender mailSender;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository, NotificationsMapper notificationsMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationsMapper = notificationsMapper;
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
    @Override
    public List<NotificationDto> userNotifications(String username) {
        Optional<List<Notification>> notifications = notificationRepository.findAllByUsername(username);
        System.out.println(notifications);
        if (notifications.isPresent()) {
            List<NotificationDto> nd = notifications.get().stream().map(notificationsMapper::notificationToNotificationDto).collect(Collectors.toList());
            System.out.println(nd);
            return nd;
        } else {
            throw new NoSuchElementException("No notifications for user " + username);
        }
    }
    @Override
    public Page<NotificationDto> allNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationsMapper::notificationToNotificationDto);
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
