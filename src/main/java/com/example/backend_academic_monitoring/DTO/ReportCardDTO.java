package com.example.backend_academic_monitoring.DTO;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportCardDTO {
    String subjectName;
    Integer firstGrade;
    Integer secondGrade;
    Integer thirdGrade;
    Integer fourthGrade;
    Integer average;
}
