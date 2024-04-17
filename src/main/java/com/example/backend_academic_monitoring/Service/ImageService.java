package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ImageDTO;
import com.example.backend_academic_monitoring.DTO.ResourceDTO;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageService {
    ImageEntity saveFile(MultipartFile file);

    ImageDTO getImage(Integer id);

    ImageDTO getImageByUuid(String uuid);

    void deleteImage(Integer userId);

    ResourceDTO getResource(String uuid) throws IOException;

    List<ImageEntity> saveFiles(MultipartFile[] files);

    String getImageURL(String uuid);
}
