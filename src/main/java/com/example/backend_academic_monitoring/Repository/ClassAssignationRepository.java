package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassAssignationRepository extends JpaRepository<ClassAssignationEntity, Integer>{
    List<ClassAssignationEntity> findAllByClassId(Integer classId);
    List<ClassAssignationEntity> findAllByClassroomId(Integer subjectId);
    List<ClassAssignationEntity> findAllByTeacherId(Integer teacherId);
    ClassAssignationEntity findByClassIdAndClassroomIdAndTeacherIdAndSubjectId(Integer classId, Integer classroomId, Integer teacherId, Integer subjectId);
    Boolean existsByClassIdAndSubjectId(Integer classId, Integer subjectId);
    ClassAssignationEntity findByClassIdAndSubjectId(Integer classId, Integer subjectId);
    @Query("select ca from ClassAssignationEntity ca " +
            "join ClassEntity  c on ca.classId = c.id join StudentEntity s on s in elements( c.students) " +
            "where s.id = :studentId and c.year = :year")
    List<ClassAssignationEntity> findAllByStudentAndYear(Integer studentId , Integer year);
}
