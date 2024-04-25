package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.LastMessageDTO;
import com.example.backend_academic_monitoring.DTO.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO saveMessage(MessageDTO messageDTO);

    List<MessageDTO> getMessagesByChatId(String chatId);

    List<LastMessageDTO> getLastMessagesByUserId(String username);

    LastMessageDTO getNotification(String chatId, String username);

    void seenMessage(MessageDTO messageDTO);
}
