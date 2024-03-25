package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;

import java.util.List;

public interface ActivityGradeService {
    void saveGrades(List<ActivityGradeEntity> activityGradeEntity);

    List<ActivityGradeEntity> getGradesByActivityId(Integer activityId);

    List<ActivityGradeEntity> getGradesByStudentIdAndAssignationId(Integer studentId, Integer assignationId);
}
