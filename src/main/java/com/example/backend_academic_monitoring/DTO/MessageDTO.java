package com.example.backend_academic_monitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDTO {
    private Integer id;
    private String content;
    private String sender;
    private String receiver;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/La_Paz")
    private Timestamp date;
    private String chatId;
    private boolean isSeen;

}
