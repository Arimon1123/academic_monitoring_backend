package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ClassListDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/{classId}")
    public ResponseEntity<ResponseDTO<ClassEntity>> getClass(@PathVariable Integer classId) {
        return ResponseEntity.ok(new ResponseDTO<>(classService.getClass(classId), "Clase obtenida", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<ClassListDTO>>> getClassByYearAndShift(@RequestParam Integer gradeId, @RequestParam Integer year, @RequestParam Integer shift) {
        try {
            LOGGER.info("Obteniendo clases por año y turno");
            return ResponseEntity.ok(new ResponseDTO<>(classService.getClassByGradeAndYearAndShift(gradeId, year, shift), "Clases obtenidas", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}

