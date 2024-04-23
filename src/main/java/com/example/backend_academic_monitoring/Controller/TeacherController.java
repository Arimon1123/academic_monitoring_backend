package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ResponseDTO<String>> saveTeacherSubjects(@RequestBody List<SubjectDTO> subjects, @PathVariable Integer teacherId) {
        try {
            teacherService.saveTeacherSubjects(teacherId, subjects);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Subjects saved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATIVE')")
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<ResponseDTO<List<TeacherDTO>>> getTeacherBySubject(@PathVariable Integer subjectId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(teacherService.getTeacherBySubject(subjectId), "Teachers retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<ResponseDTO<TeacherDTO>> findTeacherByUserId(@PathVariable Integer teacherId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(teacherService.findTeacherById(teacherId), "Teacher retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}
