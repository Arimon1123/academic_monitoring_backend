package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.AnnouncementDTO;
import com.example.backend_academic_monitoring.Entity.AnnouncementEntity;
import com.example.backend_academic_monitoring.Entity.Receiver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnnouncementMapper {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static AnnouncementDTO toDTO(AnnouncementEntity announcement) {
        AnnouncementDTO announcementDTO = new AnnouncementDTO();
        announcementDTO.setId(announcement.getId());
        announcementDTO.setDate(announcement.getDate());
        announcementDTO.setMessage(announcement.getMessage());
        announcementDTO.setReceivers(receiverToJson(announcement.getReceiver()));
        announcementDTO.setTitle(announcement.getTitle());
        announcementDTO.setPublishingDate(announcement.getPublishingDate());
        return announcementDTO;
    }

    public static AnnouncementEntity toEntity(AnnouncementDTO announcementDTO) {
        AnnouncementEntity announcement = new AnnouncementEntity();
        announcement.setId(announcementDTO.getId());
        announcement.setDate(announcementDTO.getDate());
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setMessage(announcementDTO.getMessage());
        announcement.setPublishingDate(announcementDTO.getPublishingDate());
        announcement.setReceiver(JsonToReceiver(announcementDTO.getReceivers()));
        return announcement;
    }

    public static String receiverToJson(Receiver receiver) {
        try {
            return objectMapper.writeValueAsString(receiver);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Receiver JsonToReceiver(String receiver) {
        try {
            return objectMapper.readValue(receiver, Receiver.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
