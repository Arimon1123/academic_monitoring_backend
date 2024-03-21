package com.example.backend_academic_monitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDTO {
    private Integer id;
    private String name;
    private String ci;
    private String fatherLastname;
    private String motherLastname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/La_Paz")
    private Date birthDate;
    private String address;
    private String rude;
    private String studentClass;
}
