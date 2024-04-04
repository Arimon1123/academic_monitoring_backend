package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityGradeRepository extends JpaRepository<ActivityGradeEntity, Integer> {
    List<ActivityGradeEntity> findAllByActivityId(Integer activityId);

    @Query("SELECT a FROM ActivityGradeEntity a " +
            "join ActivityEntity ae on a.activityId = ae.id " +
            "where  a.studentId = :studentId " +
            "AND ae.assignationId = :assignationId order by a.id desc")
    List<ActivityGradeEntity> findAllByStudentIdAndAssignationId(Integer studentId, Integer assignationId);
    @Query("SELECT a FROM ActivityGradeEntity a " +
            "join ActivityEntity ae on a.activityId = ae.id " +
            "where  ae.assignationId = :assignationId and ae.bimester = :bimester order by a.id asc")
    List<ActivityGradeEntity> findAllByAssignationIdAndBimester(Integer assignationId, Integer bimester);
    @Query( value = "select row_number() over () as id ,sum(grade * activity.value * dimension.value)/10000 as total_grade ,student_id, student.name, activity.bimester, subject.id as subject_id, subject.name as subject_name\n" +
            " from activity_has_grade\n" +
            " left join activity on activity_has_grade.activity_id = activity.id\n" +
            " join class_has_subject on activity.class_has_subject_id = class_has_subject.id\n" +
            " join class on class_has_subject.class_id = class.id\n" +
            " join student on activity_has_grade.student_id = student.id\n" +
            "  join subject on class_has_subject.subject_id = subject.id\n" +
            "  join dimension on activity.dimension = dimension.name\n" +
            "  where student_id =:studentId and class.year = :year \n" +
            "  group by student_id, student.name,activity.bimester, subject.name, subject.id;", nativeQuery = true)
    List<GradesDTO> findAllByStudentIdAndYear(Integer studentId, Integer year);
}
