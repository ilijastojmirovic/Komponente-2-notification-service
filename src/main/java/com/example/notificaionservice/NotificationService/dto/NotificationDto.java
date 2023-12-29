package com.example.notificaionservice.NotificationService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String link;
    private String notificationType;

    public NotificationDto(Long id, String firstName, String lastName, String email, String username, String link, String notificationType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.link = link;
        this.notificationType = notificationType;
    }


    @Override
    public String toString() {
        return "NotificationDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", link='" + link + '\'' +
                ", notificationType='" + notificationType + '\'' +
                '}';
    }
}
