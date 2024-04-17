package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AnnouncementDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AnnouncementService {
    void save(AnnouncementDTO announcementDTO, MultipartFile[] images) throws JsonProcessingException;

    void delete(Integer id);

    void update(AnnouncementDTO announcementDTO);

    List<AnnouncementDTO> getAllAnnouncements() throws JsonProcessingException;

    List<AnnouncementDTO> getByReceivers(String receiver, Integer gradeId, Integer shift);
}
