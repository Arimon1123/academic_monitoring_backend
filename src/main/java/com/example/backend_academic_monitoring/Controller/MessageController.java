package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.MessageDTO;
import com.example.backend_academic_monitoring.Service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    public static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    private final MessageService messageService;
    private final SimpMessagingTemplate template;

    public MessageController(MessageService messageService, SimpMessagingTemplate template) {
        this.messageService = messageService;
        this.template = template;
    }

    @MessageMapping("/chat")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        logger.info("message received {}", messageDTO);
        template.convertAndSend("/topic/chat/" + messageDTO.getChatId(), messageService.saveMessage(messageDTO));
        logger.info("messaged sent");
        sendNotification(messageDTO);
        logger.info("notification sent");
    }

    @MessageMapping("/notification")
    public void sendNotification(MessageDTO messageDTO) {
        String destination = "/topic/notification/" + messageDTO.getReceiver();
        logger.info("Notification destination {}", destination);
        template.convertAndSend(destination, messageService.getNotification(messageDTO.getChatId(), messageDTO.getReceiver()));
        template.convertAndSend("/topic/notification/" + messageDTO.getSender(), messageService.getNotification(messageDTO.getChatId(), messageDTO.getSender()));
    }
}
