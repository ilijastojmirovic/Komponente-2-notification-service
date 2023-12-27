package com.example.notificaionservice.NotificationService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String username;

    public NotificationCreateDto(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }
}
