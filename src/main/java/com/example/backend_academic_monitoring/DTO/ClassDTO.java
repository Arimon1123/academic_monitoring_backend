package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

@Data
public class ClassDTO {
    private Integer id;
    private Integer year;
    private Integer gradeId;
    private Integer shift;
    private String identifier;
}
