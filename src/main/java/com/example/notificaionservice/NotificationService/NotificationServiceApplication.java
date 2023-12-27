package com.example.notificaionservice.NotificationService;

import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import com.example.notificaionservice.NotificationService.service.impl.NotificationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
