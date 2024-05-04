package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

import java.sql.Time;

@Data
public class ConsultHourDTO {
    private Integer id;
    private String weekday;
    private Time startTime;
    private Time endTime;
    private Integer period;
    private Integer teacherId;
}
