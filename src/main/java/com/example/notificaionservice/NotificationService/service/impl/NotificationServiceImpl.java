package com.example.notificaionservice.NotificationService.service.impl;

import com.example.notificaionservice.NotificationService.client.ClientDto;
import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import com.example.notificaionservice.NotificationService.dto.NotificationFromManagerDto;
import com.example.notificaionservice.NotificationService.dto.NotificationScheduleMessageDto;
import com.example.notificaionservice.NotificationService.mapper.NotificationsMapper;
import com.example.notificaionservice.NotificationService.repository.NotificationRepository;
import com.example.notificaionservice.NotificationService.repository.NotificationTypeRepository;
import com.example.notificaionservice.NotificationService.service.NotificationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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


    private RestTemplate clientServiceRestTemplate;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository,
                                   NotificationsMapper notificationsMapper, RestTemplate clientServiceRestTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationsMapper = notificationsMapper;
        this.clientServiceRestTemplate = clientServiceRestTemplate;
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
    public void scheduleMessage(NotificationScheduleMessageDto notificationScheduleMessageDto) {
        NotificationType notificationType = new NotificationType("Schedule message");
        notificationTypeRepository.save(notificationType);
        Notification notificaton = new Notification(notificationScheduleMessageDto.getFirstName(), notificationScheduleMessageDto.getLastName()
                , "", notificationType, notificationScheduleMessageDto.getUsername(), notificationScheduleMessageDto.getEmail());

       String email = "Hello " + notificationScheduleMessageDto.getFirstName() + " " + notificationScheduleMessageDto.getLastName() + ",\n\n" +
                "You have made an appointment on " + notificationScheduleMessageDto.getDay() + " at " + notificationScheduleMessageDto.getTime() + " in " + notificationScheduleMessageDto.getHallName() + ".\n\n" +
                "Best Regards,\n" +
                "Support team\n" +
                "Fitness Center";
        sendEmail(notificationScheduleMessageDto.getEmail(), "Scheduling appointment", email);
        notificationRepository.save(notificaton);
    }

    @Override
    public void cancelSchedulingMessage(NotificationScheduleMessageDto notificationScheduleMessageDto) {
        NotificationType notificationType = new NotificationType("Schedule canceled message");
        notificationTypeRepository.save(notificationType);
        Notification notificaton = new Notification(notificationScheduleMessageDto.getFirstName(), notificationScheduleMessageDto.getLastName()
                , "", notificationType, notificationScheduleMessageDto.getUsername(), notificationScheduleMessageDto.getEmail());

        String email = "Hello " + notificationScheduleMessageDto.getFirstName() + " " + notificationScheduleMessageDto.getLastName() + ",\n\n" +
                "Your appointment is canceled on " + notificationScheduleMessageDto.getDay() + " at " + notificationScheduleMessageDto.getTime() + " in " + notificationScheduleMessageDto.getHallName() + ".\n\n" +
                "Best Regards,\n" +
                "Support team\n" +
                "Fitness Center";
        sendEmail(notificationScheduleMessageDto.getEmail(), "Canceled appointment", email);
        notificationRepository.save(notificaton);
    }

    @Override
    public void managerCancelSchedulingMessage(NotificationFromManagerDto notificationFromManagerDto) {
        System.out.println(notificationFromManagerDto);
        ResponseEntity<ClientDto> client = clientServiceRestTemplate.exchange("/client/clientForNotification/" + notificationFromManagerDto.getClientId(),
                HttpMethod.GET, new HttpEntity<>(notificationFromManagerDto.getClientId()), ClientDto.class);
        System.out.println(client);
        NotificationType notificationType = new NotificationType("Manager canceled message");
        notificationTypeRepository.save(notificationType);

        Notification notificaton = new Notification(client.getBody().getFirstName(), client.getBody().getLastName()
                , "", notificationType, client.getBody().getUsername(), client.getBody().getEmail());

        String email = "Hello " + client.getBody().getFirstName() + " " + client.getBody().getLastName() + ",\n\n" +
                "Your appointment is canceled on " + notificationFromManagerDto.getDay() + " at " + notificationFromManagerDto.getStartTime() + " in " + notificationFromManagerDto.getHallName() + ".\n\n" +
                "Best Regards,\n" +
                "Manager " + notificationFromManagerDto.getManagerFirstName() + " " + notificationFromManagerDto.getManagerLastName() + "\n" +
                "Fitness Center";

        sendEmail(client.getBody().getEmail(), "Manager canceled appointment", email);
        notificationRepository.save(notificaton);
    }

    @Override
    public List<NotificationDto> listNotifications(String username) {
        Optional<List<Notification>> notifications = notificationRepository.findAllByUsername(username);
        if (notifications.isPresent()) {
            List<NotificationDto> nd = notifications.get().stream().map(notificationsMapper::notificationToNotificationDto).collect(Collectors.toList());
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
