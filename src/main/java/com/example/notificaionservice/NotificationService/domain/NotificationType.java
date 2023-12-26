package com.example.notificaionservice.NotificationService.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //dodaj mi ovde notification i da moze da ima vise notifikacija
    @OneToMany(mappedBy = "notificationType")
    private Set<Notification> notifications = new HashSet<>();

    public NotificationType(String name) {
        this.name = name;
    }

    public NotificationType() {

    }
}
