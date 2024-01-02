package com.example.notificaionservice.NotificationService.service;


import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import com.example.notificaionservice.NotificationService.dto.NotificationScheduleMessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {
    void registration(NotificationCreateDto notificationCreateDto);
    void changePassword(NotificationCreateDto notificationCreateDto);
    void scheduleMessage(NotificationScheduleMessageDto notificationScheduleMessageDto);
    void cancelSchedulingMessage(NotificationScheduleMessageDto notificationScheduleMessageDto);
    List<NotificationDto> listNotifications(String username);

    Page<NotificationDto> allNotifications(Pageable pageable);

}
