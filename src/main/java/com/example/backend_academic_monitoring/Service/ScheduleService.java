package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    List<ScheduleEntity> findAllByClassId(Integer classId);

    List<ScheduleEntity> findAllByTeacherId(Integer teacherId, Integer year);

    List<ScheduleEntity> findAllByClassroomId(Integer classroomId, Integer year);

    List<ScheduleEntity> findAllByClassIdAndSubjectId(Integer classId, Integer subjectId);
}
