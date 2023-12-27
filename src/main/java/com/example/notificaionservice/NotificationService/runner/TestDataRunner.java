package com.example.notificaionservice.NotificationService.runner;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import com.example.notificaionservice.NotificationService.repository.NotificationRepository;
import com.example.notificaionservice.NotificationService.repository.NotificationTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;
    private NotificationRepository notificationRepository;


    public TestDataRunner(NotificationTypeRepository notificationTypeRepository, NotificationRepository notificationRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//        NotificationType notificationType2 = new NotificationType("Changed password");
//        NotificationType notificationType3 = new NotificationType("Successfully scheduled training");
        NotificationType notificationType4 = new NotificationType("Training canceled");
//        NotificationType notificationType5 = new NotificationType("Remainder");

       // notificationTypeRepository.save(notificationType1);
//        notificationTypeRepository.save(notificationType2);
//        notificationTypeRepository.save(notificationType3);
        notificationTypeRepository.save(notificationType4);
//        notificationTypeRepository.save(notificationType5);

        //String firstName, String lastName, String link, NotificationType notificationType, String username, String email
        Notification notification1 = new Notification("Pera", "Peric", "", notificationType4, "pera", "");
        notificationRepository.save(notification1);
    }
}
