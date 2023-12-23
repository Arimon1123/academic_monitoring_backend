package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ImageDTO;
import com.example.backend_academic_monitoring.DTO.ResourceDTO;
import com.example.backend_academic_monitoring.Service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@RequestMapping("/file")
@RestController
public class FileController {

    private final ImageService fileService;
    public static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    public FileController(ImageService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/image/{uuid}")
    public ResponseEntity<Resource> getImage(@PathVariable String uuid) throws IOException {
        ResourceDTO resource = null;
        try {
           resource = fileService.getResource(uuid);
        }
        catch (Exception e){
            LOGGER.error("Error al obtener la imagen",e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", resource.getImageDTO().getType());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.getResource().contentLength())
                .body(resource.getResource());
    }
}
