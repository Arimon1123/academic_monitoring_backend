package com.example.backend_academic_monitoring.Repository;


import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer>{


    List<ScheduleEntity> findAllByClassAssignation_ClassId(Integer class_id);
    List<ScheduleEntity> findAllByClassAssignation_TeacherId(Integer teacherId);
    List<ScheduleEntity> findAllByClassAssignation_ClassroomId(Integer classroomId);
    List<ScheduleEntity> findAllByClassAssignation_SubjectIdAndClassAssignation_ClassId(Integer subjectId, Integer classId);
}
