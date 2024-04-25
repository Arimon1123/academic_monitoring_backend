package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.MessageDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;
    private final MessageController messageController;

    public ChatController(MessageService messageService, MessageController messageController) {
        this.messageService = messageService;
        this.messageController = messageController;
    }

    @GetMapping("/messages")
    public ResponseEntity<ResponseDTO<?>> getChatMessages(@RequestParam("chatId") String chatId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(messageService.getMessagesByChatId(chatId), "Messages retrieved Successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/lastMessages/{username}")
    public ResponseEntity<ResponseDTO<?>> getLastMessages(@PathVariable("username") String username) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(messageService.getLastMessagesByUserId(username), "Last Messages retrieved Successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PutMapping("/seenMessage")
    public ResponseEntity<ResponseDTO<?>> seenMessage(@RequestBody MessageDTO messageDTO) {
        try {
            messageService.seenMessage(messageDTO);
            Thread.sleep(1000);
            messageController.sendNotification(messageDTO);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Message seen Successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}
