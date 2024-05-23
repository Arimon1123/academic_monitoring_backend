package com.example.backend_academic_monitoring.Repository;


import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    List<ScheduleEntity> findAllByClassAssignation_ClassId(Integer class_id);

    @Query(value = "Select s.* from schedule s " +
            "join public.class_has_subject chs on chs.id = s.class_has_subject_id " +
            "join class c on chs.class_id = c.id " +
            "where c.year = :year and chs.teacher_id = :teacherId ", nativeQuery = true)
    List<ScheduleEntity> findAllByClassAssignation_TeacherId(Integer teacherId, Integer year);

    @Query(value = " Select s.* from schedule s " +
            "join public.class_has_subject chs on chs.id = s.class_has_subject_id " +
            "join class c on chs.class_id = c.id " +
            "where c.year = :year and chs.classroom_id = :classroomId ", nativeQuery = true)
    List<ScheduleEntity> findAllByClassAssignation_ClassroomId(Integer classroomId, Integer year);

    List<ScheduleEntity> findAllByClassAssignation_SubjectIdAndClassAssignation_ClassId(Integer subjectId, Integer classId);
}
