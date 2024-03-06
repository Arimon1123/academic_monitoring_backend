package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping()
    public ResponseEntity<ResponseDTO<List<GradeDTO>>> getAll(){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(gradeService.getAll(),"Grades obtained correctly", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }
}
