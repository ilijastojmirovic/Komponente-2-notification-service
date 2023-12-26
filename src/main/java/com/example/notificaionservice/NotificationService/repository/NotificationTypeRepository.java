package com.example.notificaionservice.NotificationService.repository;

import com.example.notificaionservice.NotificationService.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
}
