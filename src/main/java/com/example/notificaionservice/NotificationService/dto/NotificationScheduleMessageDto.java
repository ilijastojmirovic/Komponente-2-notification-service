package com.example.notificaionservice.NotificationService.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationScheduleMessageDto {

    private String firstName;
    private String lastName;
    private String email;
    private String hallName;
    private String day;
    private String time;
    private String username;

    public NotificationScheduleMessageDto(String firstName, String lastName, String email, String hallName, String day, String time, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hallName = hallName;
        this.day = day;
        this.time = time;
        this.username = username;
    }
}
