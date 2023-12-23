package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ImageDTO;
import com.example.backend_academic_monitoring.DTO.ResourceDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Integer saveFile(MultipartFile file);
    ImageDTO getImage(Integer id);
    ImageDTO getImageByUuid(String uuid);
    void deleteImage(Integer userId);
    ResourceDTO getResource(String uuid) throws IOException;
}
