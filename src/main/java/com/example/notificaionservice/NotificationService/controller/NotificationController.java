package com.example.notificaionservice.NotificationService.controller;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Get user by token")
    @GetMapping("/listNotification")
    public ResponseEntity<Void> getClient(String token){
        //pozvati klijenta za sortiranje notifikacija
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
