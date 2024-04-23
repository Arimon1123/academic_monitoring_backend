package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.MessageDTO;
import com.example.backend_academic_monitoring.Entity.MessageEntity;
import com.example.backend_academic_monitoring.Repository.MessageRepository;
import com.example.backend_academic_monitoring.Service.MessageService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
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
        Timestamp time = messageRepository.save(newMessage).getDate();
        messageDTO.setDate(time);
        return messageDTO;
    }

    @Override
    public List<MessageDTO> getMessages(String sender, String receiver) {
        return messageRepository.findAllByReceiverAndSenderOrderByDateDesc(sender, receiver)
                .stream().map(messageEntity -> {
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setId(messageEntity.getId());
                    messageDTO.setContent(messageEntity.getContent());
                    messageDTO.setSender(messageEntity.getSender());
                    messageDTO.setReceiver(messageEntity.getReceiver());
                    messageDTO.setDate(messageEntity.getDate());
                    messageDTO.setChatId(messageEntity.getChatId());
                    return messageDTO;
                }).toList();
    }
}
