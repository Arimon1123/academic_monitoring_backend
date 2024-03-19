package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parent")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }
    public static  final Logger logger = LoggerFactory.getLogger(ParentController.class);
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<ParentDTO>>>getFatherByCi(@RequestParam(required = false) String ci){

        try {
            logger.info("querying parents by ci {}", ci);
            List<ParentDTO> parentDTOList = parentService.getParentByCi(ci);

            return ResponseEntity.ok(new ResponseDTO<>(parentDTOList, "Father found", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null ,e.getMessage(),500));
        }
    }
 }
