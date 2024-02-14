package com.example.backend_academic_monitoring.DTO;

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
    private Date birthDate;
    private String address;
}
