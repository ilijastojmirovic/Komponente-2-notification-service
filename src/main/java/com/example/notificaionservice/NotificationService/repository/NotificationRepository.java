package com.example.notificaionservice.NotificationService.repository;

import com.example.notificaionservice.NotificationService.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    //List<Notification> findAllByUsername(String username);
    Optional<List<Notification>> findAllByUsername(String username);
}
