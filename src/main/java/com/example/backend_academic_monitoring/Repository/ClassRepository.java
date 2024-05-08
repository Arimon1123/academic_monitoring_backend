package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
    List<ClassEntity> findByGrade_IdAndYearAndShift(Integer gradeId, Integer year, Integer shift);

    @Query(value = """
            SELECT COUNT(s.id)
            FROM class c
            JOIN student_class sc ON c.id = sc.class_id
            JOIN student s ON s.id = sc.student_id
            WHERE c.id = :id""", nativeQuery = true)
    Integer getStudentCount(@Param("id") Integer id);

    @Query(value = """
            SELECT c.id, c.year, c.shift, c.identifier,c.status, g.number, g.section, c.grade_id
            FROM class c
            JOIN grade g ON c.grade_id = g.id
            JOIN student_class sc ON c.id = sc.class_id
            JOIN student s ON s.id = sc.student_id
            WHERE s.id = :studentId""", nativeQuery = true)
    ClassEntity findByStudentsId(Integer studentId);

    @Query(value = """
            SELECT c.id, c.year, c.shift, c.identifier,c.status, g.number, g.section, c.grade_id
            FROM class c
            JOIN grade g ON c.grade_id = g.id
            JOIN student_class sc ON c.id = sc.class_id
            JOIN student s ON s.id = sc.student_id
            WHERE s.id = :studentId and c.year =:year and c.shift = :shift""", nativeQuery = true)
    ClassEntity findByStudentIdAndYearAndShift(Integer studentId, Integer year, Integer shift);

    @Query(value = """
            SELECT c from ClassEntity c join ClassAssignationEntity ca on c.id = ca.classId\s
            where ca.id = :assignationId and c.status = 1""")
    ClassEntity findByAssignation(Integer assignationId);

}
