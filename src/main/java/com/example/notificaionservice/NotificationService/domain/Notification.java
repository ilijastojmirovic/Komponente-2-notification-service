package com.example.notificaionservice.NotificationService.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String link;
    private String email;
    private String username;

    //dodaj mi ovde i notificationtype ali da notifaction ima samo jedan notificationtype
    @ManyToOne
    @JoinColumn(name = "notification_type_id")
    private NotificationType notificationType;


    public Notification(String firstName, String lastName, String link, NotificationType notificationType, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.link = link;
        this.notificationType = notificationType;
        this.username = username;
        this.email = email;
    }

    public Notification() {
    }
}
