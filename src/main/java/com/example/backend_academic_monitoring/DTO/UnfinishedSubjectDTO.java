package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

@Data
public class UnfinishedSubjectDTO {
    private Integer bimester;
    private Integer subjectId;
    private String subjectName;
    private Integer assignationId;
    private String teacherName;
    private String teacherLastName;
    private String gradeNumber;
    private String section;
    private String identifier;
    private Integer shift;
}
