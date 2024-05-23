package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.UnfinishedSubjectDTO;
import com.example.backend_academic_monitoring.Entity.ConfigEntity;
import com.example.backend_academic_monitoring.Service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
    private static final Logger log = LoggerFactory.getLogger(ConfigurationController.class);
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping("/finishBimester")
    public ResponseEntity<?> finishBimester() {
        List<UnfinishedSubjectDTO> list = configurationService.finishBimester();
        if (list.isEmpty()) {
            return ResponseEntity.ok(new ResponseDTO<String>(null, "Bimester finished successfully", 204));
        }
        return ResponseEntity.ok(new ResponseDTO<>(list, "Bimester couldn't be finished", 200));
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO<ConfigEntity>> getCurrentBimester() {
        log.info("retrieving current config");
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        configurationService.getCurrentConfig(), "Configuration retrieved successfully", 200));
    }

    @PostMapping("/finishYear")
    public ResponseEntity<?> finishYear() {
        try {
            configurationService.finishYear();
            return ResponseEntity.ok(new ResponseDTO<String>(null, "Year finished successfully", 200));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseDTO<String>(null, e.getMessage(), 200));
        }
    }
}
