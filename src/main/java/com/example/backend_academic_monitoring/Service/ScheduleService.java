package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    List<ScheduleEntity> findAllByClassId(Integer classId);
    List<ScheduleEntity> findAllByTeacherId(Integer teacherId);
    List<ScheduleEntity> findAllByClassroomId(Integer classroomId);
    List<ScheduleEntity> findAllByClassIdAndSubjectId(Integer classId, Integer subjectId);
}
