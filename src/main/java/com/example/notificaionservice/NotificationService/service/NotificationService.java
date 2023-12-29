package com.example.notificaionservice.NotificationService.service;


import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    void registration(NotificationCreateDto notificationCreateDto);
    void changePassword(NotificationCreateDto notificationCreateDto);
    List<NotificationDto> listNotifications(String username);

}
