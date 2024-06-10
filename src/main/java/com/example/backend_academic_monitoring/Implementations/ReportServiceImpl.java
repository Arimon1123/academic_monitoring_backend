package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradeRangeReportDTO;
import com.example.backend_academic_monitoring.DTO.ReportAttendanceDTO;
import com.example.backend_academic_monitoring.DTO.ReportPerformanceDTO;
import com.example.backend_academic_monitoring.Repository.ReportRepository;
import com.example.backend_academic_monitoring.Service.ReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<ReportPerformanceDTO> getPerformanceReport(Integer gradeId, Integer year) {
        return reportRepository.getPerformanceReport(gradeId, year).stream().map(
                objects -> {
                    ReportPerformanceDTO reportPerformanceDTO = new ReportPerformanceDTO();
                    reportPerformanceDTO.setId((Long) objects[0]);
                    reportPerformanceDTO.setGrade((BigDecimal) objects[1]);
                    reportPerformanceDTO.setClassId((Integer) objects[2]);
                    reportPerformanceDTO.setYear((Integer) objects[3]);
                    reportPerformanceDTO.setShift((Integer) objects[4]);
                    reportPerformanceDTO.setIdentifier((String) objects[5]);
                    reportPerformanceDTO.setGradeNumber((String) objects[6]);
                    reportPerformanceDTO.setBimester((Integer) objects[7]);
                    return reportPerformanceDTO;
                }
        ).toList();
    }

    @Override
    public List<ReportAttendanceDTO> getAttendanceReport(Date startDate, Date endDate, Integer gradeId) {
        return reportRepository.getAttendanceReport(startDate, endDate, gradeId).stream().map(
                report -> {
                    ReportAttendanceDTO newReport = new ReportAttendanceDTO();
                    newReport.setAttendance((Double) report[0]);
                    newReport.setCount((Long) report[1]);
                    newReport.setClasses((Long) report[2]);
                    newReport.setSubjects((Long) report[3]);
                    newReport.setAttendanceType((Integer) report[4]);
                    newReport.setYear((Integer) report[5]);
                    newReport.setShift((Integer) report[6]);
                    newReport.setIdentifier((String) report[7]);
                    newReport.setSection((String) report[8]);
                    newReport.setGradeNumber((String) report[9]);
                    return newReport;
                }
        ).toList();
    }

    @Override
    public List<GradeRangeReportDTO> getGradeRangeReport(Integer bimester, Integer gradeId, Integer year) {
        return reportRepository.getGradeRangesReport(bimester, gradeId, year).stream().map(
                report -> {
                    GradeRangeReportDTO newReport = new GradeRangeReportDTO();
                    newReport.setRange((String) report[0]);
                    newReport.setCount((Long) report[1]);
                    newReport.setTotal((BigDecimal) report[2]);
                    return newReport;
                }
        ).toList();
    }
}
