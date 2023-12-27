package com.example.notificaionservice.NotificationService.service;


import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;

public interface NotificationService {
    void registration(NotificationCreateDto notificationCreateDto);
    void changePassword(NotificationCreateDto notificationCreateDto);

}
