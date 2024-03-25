package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityGradeRepository extends JpaRepository<ActivityGradeEntity, Integer> {
    List<ActivityGradeEntity> findAllByActivityId(Integer activityId);

    @Query("SELECT a FROM ActivityGradeEntity a " +
            "join ActivityEntity ae on a.activityId = ae.id " +
            "where  a.studentId = :studentId " +
            "AND ae.assignationId = :assignatioId order by a.id desc")
    List<ActivityGradeEntity> findAllByStudentIdAndAssignationId(Integer studentId, Integer assignationId);
}
