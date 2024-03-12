package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassSubjectDTO {
    private Integer id;
    private String className;
    private String teacherName;
    private String subjectName;
    private String classroomName;
    private List<ScheduleEntity> schedule;
}
