package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AnnouncementDTO;
import com.example.backend_academic_monitoring.Entity.AnnouncementEntity;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import com.example.backend_academic_monitoring.Mappers.AnnouncementMapper;
import com.example.backend_academic_monitoring.Repository.AnnouncementRepository;
import com.example.backend_academic_monitoring.Service.AnnouncementService;
import com.example.backend_academic_monitoring.Service.ImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
    private final AnnouncementRepository announcementRepository;
    private final ImageService imageService;


    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository, ImageService imageService) {
        this.announcementRepository = announcementRepository;
        this.imageService = imageService;
    }

    @Override
    public void save(AnnouncementDTO announcementDTO, MultipartFile[] images) throws JsonProcessingException {
        LOGGER.info("announcement {}", announcementDTO);
        AnnouncementEntity announcementEntity = AnnouncementMapper.toEntity(announcementDTO);
        List<ImageEntity> imageEntities = imageService.saveFiles(images);
        announcementEntity.setImages(imageEntities);
        announcementRepository.save(announcementEntity);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(AnnouncementDTO announcementDTO) {

    }

    @Override
    public List<AnnouncementDTO> getAllAnnouncements() throws JsonProcessingException {
        List<AnnouncementEntity> announcementEntities = announcementRepository.findAll();
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        for (AnnouncementEntity announcementEntity : announcementEntities) {
            LOGGER.info("AnnouncementEntity: {}", announcementEntity.getReceiver());
            AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcementEntity);
            announcementDTO.setImages(imageUrl(announcementEntity.getImages()));
            announcementDTOS.add(announcementDTO);
        }
        return announcementDTOS;
    }

    @Override
    public List<AnnouncementDTO> getByReceivers(String receiver, Integer gradeId, Integer shift) {
        List<AnnouncementEntity> announcementEntities = announcementRepository.findAllByReceiver(receiver, gradeId, shift);
        LOGGER.info("announcements {}", announcementEntities);
        List<AnnouncementDTO> announcementDTOS = new ArrayList<>();
        for (AnnouncementEntity announcement : announcementEntities) {
            AnnouncementDTO announcementDTO = AnnouncementMapper.toDTO(announcement);
            announcementDTO.setImages(imageUrl(announcement.getImages()));
            announcementDTOS.add(announcementDTO);
        }
        return announcementDTOS;
    }

    public List<String> imageUrl(List<ImageEntity> images) {
        List<String> imagesUrls = new ArrayList<>();
        for (ImageEntity imageEntity : images) {
            imagesUrls.add(imageService.getImageURL(imageEntity.getUuid()));
        }
        return imagesUrls;
    }
}
