package com.example.backend_academic_monitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public interface LastMessageDTO {
    Integer getId();

    String getContent();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/La_Paz")
    Timestamp getDate();

    String getSender();

    String getReceiver();

    String getChatId();

    Integer getUserId();

    String getUsername();

    String getName();

    String getLastname();

    String getImageUrl();

    String getUuid();

    boolean getIsSeen();
}
