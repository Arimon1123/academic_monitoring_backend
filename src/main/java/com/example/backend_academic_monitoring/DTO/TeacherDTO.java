package com.example.backend_academic_monitoring.DTO;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherDTO {
    private Integer id;
    private PersonDTO person;
    private String academicEmail;
}
