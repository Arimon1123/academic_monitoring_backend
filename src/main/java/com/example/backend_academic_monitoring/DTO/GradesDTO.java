package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradesDTO {
    public Integer id;
    public Integer studentId;
    public Integer totalGrade;
    public String name;
    public Integer bimester;
    public Integer subjectId;
    public String subjectName;
}
