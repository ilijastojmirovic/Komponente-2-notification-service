package com.example.notificaionservice.NotificationService.listener;

import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.dto.NotificationFromManagerDto;
import com.example.notificaionservice.NotificationService.dto.NotificationScheduleMessageDto;
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
    }

    @JmsListener(destination = "${destination.registrationMessage}", concurrency = "5-10")
    public void registrationMessage(Message message) throws JMSException {
        NotificationCreateDto notificationCreateDto = messageHelper.getMessage(message, NotificationCreateDto.class);
        notificationService.registration(notificationCreateDto);
    }

    @JmsListener(destination = "${destination.changedPasswordMessage}", concurrency = "5-10")
    public void changedPasswordMessage(Message message) throws JMSException {
        NotificationCreateDto notificationCreateDto = messageHelper.getMessage(message, NotificationCreateDto.class);
        notificationService.changePassword(notificationCreateDto);
    }

    @JmsListener(destination = "scheduling_message", concurrency = "5-10")
    public void schedulingMessage(Message message) throws JMSException {
        NotificationScheduleMessageDto notificationScheduleMessageDto = messageHelper.getMessage(message, NotificationScheduleMessageDto.class);
        notificationService.scheduleMessage(notificationScheduleMessageDto);
    }
    @JmsListener(destination = "cancel_scheduling_message", concurrency = "5-10")
    public void cancelSchedulingMessage(Message message) throws JMSException {
        NotificationScheduleMessageDto notificationScheduleMessageDto = messageHelper.getMessage(message, NotificationScheduleMessageDto.class);
        notificationService.cancelSchedulingMessage(notificationScheduleMessageDto);
    }

    @JmsListener(destination = "manager_cancel_scheduling_message", concurrency = "5-12")
    public void managerCancelSchedulingMessage(Message message) throws JMSException {
        NotificationFromManagerDto notificationFromManagerDto = messageHelper.getMessage(message, NotificationFromManagerDto.class);
        notificationService.managerCancelSchedulingMessage(notificationFromManagerDto);
    }

}
