package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/report")
public class ReportController {
    private static final Logger log = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/performance")
    public ResponseEntity<ResponseDTO<?>> getPerformanceReport(@RequestParam() Integer gradeId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(reportService.getPerformanceReport(gradeId), "Performance report retrieved successfully", 200));
        } catch (Exception e) {
            log.error("Error getting performance report", e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, "Error getting performance report", 500));
        }
    }

    @GetMapping("/attendance")
    public ResponseEntity<ResponseDTO<?>> getAttendanceReport(@RequestParam() Date startDate, @RequestParam() Date endDate, @RequestParam() Integer gradeId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(reportService.getAttendanceReport(startDate, endDate, gradeId), "Attendance report retrieved successfully", 200));
        } catch (Exception e) {
            log.error("Error getting attendance report", e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, "Error getting attendance report", 500));
        }
    }

    @GetMapping("/gradeRange")
    public ResponseEntity<ResponseDTO<?>> getGradeRangeReport(@RequestParam("bimester") Integer bimester, @RequestParam() Integer gradeId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(reportService.getGradeRangeReport(bimester, gradeId), "Grade range report retrieved successfully", 200));
        } catch (Exception e) {
            log.error("Error getting grade range report", e);
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null, "Error getting grade range report", 500));
        }
    }
}
