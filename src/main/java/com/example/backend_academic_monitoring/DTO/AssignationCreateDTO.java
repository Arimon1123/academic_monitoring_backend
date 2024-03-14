package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AssignationCreateDTO {
    private Integer classId;
    private Integer classroomId;
    private Integer subjectId;
    private Integer teacherId;
    List<ScheduleEntity> schedule;
}
