package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityGradeRepository extends JpaRepository<ActivityGradeEntity, Integer> {
    List<ActivityGradeEntity> findAllByActivityId(Integer activityId);

    @Query("SELECT a FROM ActivityGradeEntity a " +
            "join ActivityEntity ae on a.activityId = ae.id " +
            "where  a.studentId = :studentId " +
            "AND ae.assignationId = :assignationId AND ae.bimester = :bimester order by a.id desc")
    List<ActivityGradeEntity> findAllByStudentIdAndAssignationId(Integer studentId, Integer assignationId, Integer bimester);

    @Query("SELECT a FROM ActivityGradeEntity a " +
            "join ActivityEntity ae on a.activityId = ae.id " +
            "where  ae.assignationId = :assignationId and ae.bimester = :bimester order by a.id asc")
    List<ActivityGradeEntity> findAllByAssignationIdAndBimester(Integer assignationId, Integer bimester);

    @Query(value = """
            select row_number() over () as id ,sum(grade * activity.value * dimension.value)/10000 as total_grade ,
                   student_id, student.name, activity.bimester, subject.id as subject_id,
                   subject.name as subject_name, class_has_subject.id as assignation_id
             from activity_has_grade
             left join activity on activity_has_grade.activity_id = activity.id
             join class_has_subject on activity.class_has_subject_id = class_has_subject.id
             join class on class_has_subject.class_id = class.id
             join student on activity_has_grade.student_id = student.id
              join subject on class_has_subject.subject_id = subject.id
              join dimension on activity.dimension = dimension.name
              where student_id =:studentId and class.year = :year\s
              group by student_id, student.name,activity.bimester, subject.name, subject.id, class_has_subject.id;""", nativeQuery = true)
    List<GradesDTO> findAllByStudentIdAndYear(Integer studentId, Integer year);
}
