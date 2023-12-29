package com.example.notificaionservice.NotificationService.service;


import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    void registration(NotificationCreateDto notificationCreateDto);
    void changePassword(NotificationCreateDto notificationCreateDto);
    List<NotificationDto> userNotifications(String username);
    Page<NotificationDto> allNotifications(Pageable pageable);
}
