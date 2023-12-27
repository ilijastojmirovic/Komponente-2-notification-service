package com.example.notificaionservice.NotificationService.controller;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Operation(summary = "Notification")
    @PostMapping("/activation")
    public ResponseEntity<Void> notification(@RequestBody NotificationCreateDto notificationCreateDto) {
        notificationService.add(notificationCreateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
