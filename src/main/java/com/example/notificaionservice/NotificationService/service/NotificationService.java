package com.example.notificaionservice.NotificationService.service;


import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;

public interface NotificationService {
    void add(NotificationCreateDto notificationCreateDto);
}
