package com.example.notificaionservice.NotificationService.repository;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {


}
