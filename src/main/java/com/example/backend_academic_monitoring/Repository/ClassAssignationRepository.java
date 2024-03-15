package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassAssignationRepository extends JpaRepository<ClassAssignationEntity, Integer>{
    List<ClassAssignationEntity> findAllByClassId(Integer classId);
    List<ClassAssignationEntity> findAllByClassroomId(Integer subjectId);
    List<ClassAssignationEntity> findAllByTeacherId(Integer teacherId);
    ClassAssignationEntity findByClassIdAndClassroomIdAndTeacherIdAndSubjectId(Integer classId, Integer classroomId, Integer teacherId, Integer subjectId);
    Boolean existsByClassIdAndClassroomIdAndTeacherIdAndSubjectId(Integer classId, Integer classroomId, Integer teacherId, Integer subjectId);
}
