package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {
    private Integer id;
    private PersonDTO person;
    private String academicEmail;
    private List<SubjectDTO> subjects;
    private List<ConsultHourDTO> consultHours;
}
