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

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<ScheduleEntity>>> findAllByClassId(@RequestParam(value = "") Integer classId) {
        return ResponseEntity.ok(new ResponseDTO<>(scheduleService.findAllByClassId(classId),null, 200));
    }
}
