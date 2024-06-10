package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.AnnouncementDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.AnnouncementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/announcement")
@RestController
public class AnnouncementController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<?>> saveAnnouncement(@RequestParam(value = "announcement") String announcementDTO,
                                                           @RequestParam(value = "images") MultipartFile[] images) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.info("AnnouncementDTO: {}", announcementDTO);
            AnnouncementDTO announcement = objectMapper.readValue(announcementDTO, AnnouncementDTO.class);
            announcementService.save(announcement, images);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Anuncio guardado exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, "Error al guardar el anuncio: " + e.getMessage(), 500));
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO<?>> getAllAnnouncements() throws JsonProcessingException {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(announcementService.getAllAnnouncements(), "Anuncios obtenidos exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, "Error al obtener los anuncios: " + e.getMessage(), 500));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<?>> getAnnouncementBy(@RequestParam("receiver") String receiver,
                                                            @RequestParam("gradeId") Integer gradeId,
                                                            @RequestParam("shift") Integer shift) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(announcementService.getByReceivers(receiver, gradeId, shift), "Anuncio obtenido exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, "Error al obtener el anuncio: " + e.getMessage(), 500));
        }
    }
}