package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

@Data
public class ReportAttendanceDTO {
    double attendance;
    Long count;
    Long classes;
    Long subjects;
    Integer attendanceType;
    Integer year;
    Integer shift;
    String Identifier;
    String section;
    String gradeNumber;
}
