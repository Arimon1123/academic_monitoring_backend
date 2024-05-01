package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReportPerformanceDTO {
    private Long id;
    private BigDecimal grade;
    private Integer classId;
    private Integer year;
    private Integer shift;
    private String identifier;
    private String gradeNumber;
    private Integer bimester;
}
