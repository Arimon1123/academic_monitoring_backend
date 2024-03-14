package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import com.example.backend_academic_monitoring.Service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassId(@PathVariable Integer classId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassId(classId),null, 200));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByTeacherId(@PathVariable Integer teacherId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByTeacherId(teacherId),null, 200));
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassroomId(@PathVariable Integer classroomId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassroomId(classroomId),null, 200));
    }
}
