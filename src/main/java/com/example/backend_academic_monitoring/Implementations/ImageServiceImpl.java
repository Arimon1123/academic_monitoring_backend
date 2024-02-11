package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ImageDTO;
import com.example.backend_academic_monitoring.DTO.ResourceDTO;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import com.example.backend_academic_monitoring.Repository.ImageRepository;
import com.example.backend_academic_monitoring.Service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Integer saveFile(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String userDirectoryPath = System.getProperty("user.dir");
        LOGGER.info("userDirectoryPath {}",userDirectoryPath);
        if(!new File(userDirectoryPath + "/images").exists()){
            new File(userDirectoryPath + "/images").mkdir();
        }

        try {
            ImageEntity imageDTO = new ImageEntity();
            imageDTO.setUuid(uuid.toString());
            imageDTO.setType(file.getContentType());
            imageDTO.setName(file.getOriginalFilename());
            LOGGER.info("DTO {}",imageDTO);
            imageDTO = imageRepository.save(imageDTO);
            file.transferTo(new File(userDirectoryPath +"/images/" + imageDTO.getUuid()));
            return imageDTO.getId();
        } catch (IOException ex) {
            throw new RuntimeException("Archivo Invalido");
        }

    }

    @Override
    public ImageDTO getImage(Integer id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow();
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(imageEntity.getId());
        imageDTO.setName(imageEntity.getName());
        imageDTO.setType(imageEntity.getType());
        imageDTO.setUuid(imageEntity.getUuid());
        return imageDTO;
    }

    @Override
    public ImageDTO getImageByUuid(String uuid) {
        ImageEntity imageEntity = imageRepository.findByUuid(uuid);
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(imageEntity.getId());
        imageDTO.setName(imageEntity.getName());
        imageDTO.setType(imageEntity.getType());
        imageDTO.setUuid(imageEntity.getUuid());
        return imageDTO;
    }

    @Override
    public void deleteImage(Integer userId) {
        ImageEntity imageEntity = imageRepository.findByUserId(userId);
        LOGGER.info("imageEntity {}",imageEntity);
        String userDirectoryPath = System.getProperty("user.dir");
        File file = new File(userDirectoryPath + "/images/" + imageEntity.getUuid());
        file.delete();
        imageRepository.delete(imageEntity);
    }
    public ResourceDTO getResource(String uuid) throws IOException {
        ResourceDTO resourceDTO = new ResourceDTO();
        ImageDTO imageDTO = getImageByUuid(uuid);
        File file = new File(System.getProperty("user.dir") + "/images/" + imageDTO.getUuid());
        byte[] fileContent = Files.readAllBytes(file.toPath());
        Resource resource = new ByteArrayResource(fileContent);
        resourceDTO.setImageDTO(imageDTO);
        resourceDTO.setResource(resource);
        return resourceDTO;
    }
}