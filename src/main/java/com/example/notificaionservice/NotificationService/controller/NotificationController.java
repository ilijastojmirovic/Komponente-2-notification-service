package com.example.notificaionservice.NotificationService.controller;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationDto;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
//
//    @Operation(summary = "Get notifications by username")
//    @GetMapping("/{username}")
//    public List<NotificationDto> showNotifications(@PathVariable String username) {
//        List<NotificationDto> notificationDtos = notificationService.listNotifications(username);
//        System.out.println(notificationDtos);
//        return notificationDtos;
//    }
    @Operation(summary = "Get notifications by username")
    @GetMapping("/test")
    public void showNotifications() {
        System.out.println("test");
    }

    @Operation(summary = "Get notifications by username")
    @GetMapping("/{username}")
    public ResponseEntity<List<NotificationDto>> showNotifications(@PathVariable String username) {
        List<NotificationDto> notificationDtos = notificationService.listNotifications(username);
        return  new ResponseEntity<>(notificationDtos, HttpStatus.OK);
    }

    @Operation(summary = "Get notifications by username")
    @GetMapping("/allNotifications")
    public ResponseEntity<Page<NotificationDto>> allNotifications(Pageable pageable) {
        return  new ResponseEntity<>(notificationService.allNotifications(pageable), HttpStatus.OK);
    }
}
