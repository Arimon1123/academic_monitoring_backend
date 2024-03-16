package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer>{
    List<SubjectEntity> findAllByGradeIdAndStatus(Integer grade_id, Integer status);
    List<SubjectEntity> findAllByStatus (Integer status);
    @Query("SELECT s FROM SubjectEntity s " +
            "JOIN TeacherSubjectEntity ts ON s.id = ts.subjectId " +
            "WHERE ts.teacherId = :teacherId AND s.status = 1")
    List<SubjectEntity> findAllByTeacher (Integer teacherId);
    @Query("""
            SELECT s FROM SubjectEntity s WHERE s.grade.id = :gradeId AND s.status = 1 AND s.id NOT IN 
            (SELECT s.id FROM SubjectEntity s  LEFT JOIN ClassAssignationEntity cs ON s.id = cs.subjectId
            where cs.classId = :classId group by s.id)""")
    List<SubjectEntity> findAllByClassIdWithoutAssignation(Integer classId, Integer gradeId);
}
