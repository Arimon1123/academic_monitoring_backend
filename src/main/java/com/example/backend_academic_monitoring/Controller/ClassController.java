package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/{classId}")
    public ResponseEntity<ResponseDTO<ClassEntity>> getClass(@PathVariable Integer classId) {
        return ResponseEntity.ok(new ResponseDTO<>(classService.getClass(classId), "Clase obtenida" ,200));
    }
}

