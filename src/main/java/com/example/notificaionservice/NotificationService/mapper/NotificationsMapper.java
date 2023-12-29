package com.example.notificaionservice.NotificationService.mapper;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationsMapper {

    public NotificationDto notificationToNotificationDto(Notification notification){
        return new NotificationDto(notification.getId(),notification.getFirstName(),notification.getLastName(),notification.getEmail(),notification.getUsername()
                ,notification.getLink(),notification.getNotificationType().getName());
    }

}
