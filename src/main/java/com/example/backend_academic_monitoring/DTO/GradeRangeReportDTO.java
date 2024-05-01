package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GradeRangeReportDTO {
    String range;
    Long count;
    BigDecimal total;
}
