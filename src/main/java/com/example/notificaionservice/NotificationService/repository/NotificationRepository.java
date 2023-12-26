package com.example.notificaionservice.NotificationService.repository;

import com.example.notificaionservice.NotificationService.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
