package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.MessageDTO;

import java.util.List;

public interface MessageService {
    MessageDTO saveMessage(MessageDTO messageDTO);

    List<MessageDTO> getMessages(String sender, String receiver);
}
