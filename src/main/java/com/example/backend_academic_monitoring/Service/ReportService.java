package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.GradeRangeReportDTO;
import com.example.backend_academic_monitoring.DTO.ReportAttendanceDTO;
import com.example.backend_academic_monitoring.DTO.ReportPerformanceDTO;

import java.sql.Date;
import java.util.List;

public interface ReportService {
    List<ReportPerformanceDTO> getPerformanceReport(Integer gradeId);

    List<ReportAttendanceDTO> getAttendanceReport(Date startDate, Date endDate, Integer gradeId);

    List<GradeRangeReportDTO> getGradeRangeReport(Integer bimester, Integer gradeId);
}
