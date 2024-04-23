package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    List<MessageEntity> findAllByReceiverAndSenderOrderByDateDesc(String receiver, String sender);
}
