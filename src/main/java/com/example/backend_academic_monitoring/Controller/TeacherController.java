package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/{teacherId}/subject")
    public ResponseEntity<ResponseDTO<String>> saveTeacherSubjects(@RequestBody List<SubjectDTO> subjects, @PathVariable Integer teacherId){
        try {
            teacherService.saveTeacherSubjects(teacherId, subjects);
            return ResponseEntity.ok(new ResponseDTO<>(null,"Subjects saved successfully",200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null,e.getMessage(),500));
        }
    }
}
