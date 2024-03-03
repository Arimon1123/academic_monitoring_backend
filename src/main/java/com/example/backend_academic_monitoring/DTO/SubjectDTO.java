package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDTO {
    private Integer id;
    private String name;
    private Integer hours;
    private Integer status;
    private String gradeName;
    private String section;
}
