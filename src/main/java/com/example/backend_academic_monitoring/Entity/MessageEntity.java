package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    @Column(name = "sender", nullable = false, length = 100)
    private String sender;

    @Column(name = "receiver", nullable = false, length = 100)
    private String receiver;

    @Column(name = "chat_id", nullable = false, length = 100)
    private String chatId;

    @Column(name = "is_seen", nullable = false)
    private boolean isSeen;


}
