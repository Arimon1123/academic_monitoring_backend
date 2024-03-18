package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ImageDTO;
import com.example.backend_academic_monitoring.DTO.ResourceDTO;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageEntity saveFile(MultipartFile file);

    ImageDTO getImage(Integer id);

    ImageDTO getImageByUuid(String uuid);

    void deleteImage(Integer userId);

    ResourceDTO getResource(String uuid) throws IOException;

    ImageDTO getImageByUserId(Integer userId);

    String getImageURL(String uuid);
}
