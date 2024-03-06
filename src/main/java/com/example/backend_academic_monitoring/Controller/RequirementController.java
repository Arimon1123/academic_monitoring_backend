package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.RequirementEntity;
import com.example.backend_academic_monitoring.Service.RequirementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/requirement")
public class RequirementController {
    private final RequirementService requirementService;

    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping()
    public ResponseEntity<ResponseDTO<List<RequirementEntity>>> getRequirements(){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(requirementService.getRequirements(),"Requisitos obtenidos correctamente", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }
}
