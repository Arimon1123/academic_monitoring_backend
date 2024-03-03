package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectCreateDTO extends SubjectDTO{

    private Integer gradeId;

}
