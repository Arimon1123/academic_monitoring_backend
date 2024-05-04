package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import com.example.backend_academic_monitoring.Service.ScheduleService;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final TeacherService teacherService;

    public ScheduleController(ScheduleService scheduleService, TeacherService teacherService) {
        this.scheduleService = scheduleService;
        this.teacherService = teacherService;
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassId(@PathVariable Integer classId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassId(classId), null, 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByTeacherId(@PathVariable Integer teacherId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByTeacherId(teacherId), null, 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassroomId(@PathVariable Integer classroomId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassroomId(classroomId), null, 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/class/{classId}/subject/{subjectId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassIdAndSubjectId(@PathVariable Integer classId, @PathVariable Integer subjectId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassIdAndSubjectId(classId, subjectId), "Schedules retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/teacher/{teacherId}/consultHours")
    public ResponseEntity<?> findTeacherConsultHoursById(@PathVariable Integer teacherId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(teacherService.getConsultHours(teacherId), "Consult hours retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }

    }
}
