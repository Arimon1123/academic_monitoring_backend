package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.AttendanceDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.AttendanceEntity;
import com.example.backend_academic_monitoring.Service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/assignation/{assignationId}")
    public ResponseEntity<ResponseDTO<List<AttendanceEntity>>> getAllAttendanceByAssignation(@PathVariable Integer assignationId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(attendanceService.getAllAttendanceByAssignation(assignationId), "Attendance list retrieved successfully", 200));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<AttendanceEntity>> createAttendance(@RequestBody List<AttendanceEntity> attendanceEntity){
        try{
            attendanceService.createAttendance(attendanceEntity);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Attendance created successfully", 200));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/assignation/{assignationId}/date")
    public ResponseEntity<ResponseDTO<List<AttendanceDTO>>> getAttendanceDateByAssignationId(@PathVariable Integer assignationId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(attendanceService.getAttendanceDateByAssignationId(assignationId), "Attendance date list retrieved successfully", 200));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}
