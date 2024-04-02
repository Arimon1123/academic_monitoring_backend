package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.GradesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/grades")
@RestController
public class GradesController {
    private final GradesService gradesService;

    public GradesController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<GradesDTO>>> getAllGradesByStudentAndYear(@RequestParam Integer studentId,@RequestParam Integer year) {
        try{
            List<GradesDTO> grades = gradesService.getAllGradesByStudentAndYear(studentId, year);
            return ResponseEntity.ok(new ResponseDTO<>(grades, "Grades retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null,e.getMessage(),500));
        }
    }
}
