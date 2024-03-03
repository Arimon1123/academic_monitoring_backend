package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCreateDTO extends StudentDTO{
    private Integer classId;
    private Integer FatherId;
}
