package com.example.notificaionservice.NotificationService.listener;

import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener {

    private MessageHelper messageHelper;
    private NotificationService notificationService;

    public RegistrationListener(MessageHelper messageHelper, NotificationService notificationService) {
        this.messageHelper = messageHelper;
        this.notificationService = notificationService;
        System.out.println("dsadasdasdsa");
    }

    @JmsListener(destination = "${destination.registrationMessage}", concurrency = "5-10")
    public void registrationMessage(Message message) throws JMSException {
        System.out.println("message321321321312321");
        System.out.println(message);
        NotificationCreateDto notificationCreateDto = messageHelper.getMessage(message, NotificationCreateDto.class);
        System.out.println(notificationCreateDto);
        notificationService.add(notificationCreateDto);
    }
}
