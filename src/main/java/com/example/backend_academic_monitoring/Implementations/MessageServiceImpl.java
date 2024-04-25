package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.LastMessageDTO;
import com.example.backend_academic_monitoring.DTO.MessageDTO;
import com.example.backend_academic_monitoring.Entity.MessageEntity;
import com.example.backend_academic_monitoring.Repository.MessageRepository;
import com.example.backend_academic_monitoring.Service.ImageService;
import com.example.backend_academic_monitoring.Service.MessageService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository, ImageService imageService) {
        this.messageRepository = messageRepository;
    }

    @Override
    public MessageDTO saveMessage(MessageDTO messageDTO) {
        MessageEntity newMessage = new MessageEntity();
        newMessage.setContent(messageDTO.getContent());
        newMessage.setSender(messageDTO.getSender());
        newMessage.setReceiver(messageDTO.getReceiver());
        newMessage.setDate(new Timestamp(System.currentTimeMillis()));
        newMessage.setChatId(messageDTO.getChatId());
        newMessage.setSeen(messageDTO.isSeen());
        MessageEntity message = messageRepository.save(newMessage);
        messageDTO.setId(message.getId());
        messageDTO.setDate(message.getDate());
        return messageDTO;
    }

    @Override
    public List<MessageDTO> getMessagesByChatId(String chatId) {
        return messageRepository.findAllByChatIdOrderByDateDesc(chatId)
                .stream().map(messageEntity -> {
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setId(messageEntity.getId());
                    messageDTO.setContent(messageEntity.getContent());
                    messageDTO.setSender(messageEntity.getSender());
                    messageDTO.setReceiver(messageEntity.getReceiver());
                    messageDTO.setDate(messageEntity.getDate());
                    messageDTO.setChatId(messageEntity.getChatId());
                    messageDTO.setSeen(messageEntity.isSeen());
                    return messageDTO;
                }).toList();
    }

    @Override
    public List<LastMessageDTO> getLastMessagesByUserId(String username) {
        return messageRepository.findLastMessages(username);
    }

    @Override
    public LastMessageDTO getNotification(String chatId, String username) {
        return messageRepository.getNotification(chatId, username);
    }

    @Override
    public void seenMessage(MessageDTO messageDTO) {
        MessageEntity messageEntity = messageRepository.findById(messageDTO.getId()).orElseThrow();
        messageEntity.setSeen(true);
        messageRepository.save(messageEntity);
    }
}
