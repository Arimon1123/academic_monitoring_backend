package com.example.backend_academic_monitoring.DTO;

import lombok.*;

import java.time.Year;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassListDTO {
    private Integer id;
    private Integer year;
    private Integer shift;
    private String grade;
    private String identifier;
    private Integer studentCount;
}
