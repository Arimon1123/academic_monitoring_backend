package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    @Query("SELECT t FROM TeacherEntity t , TeacherSubjectEntity  ts  " +
            "where t.id = ts.teacherId and ts.subjectId = :subjectId " +
            "and t.status = 1")
    List<TeacherEntity> findBySubjectId(@Param("subjectId") Integer subjectId);

    TeacherEntity findByPerson_UserId(Integer userId);

    boolean existsByAcademicEmail(String academicEmail);
}
