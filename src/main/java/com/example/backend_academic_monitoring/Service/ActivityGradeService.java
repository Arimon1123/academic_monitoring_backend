package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;

import java.util.List;
import java.util.Map;

public interface ActivityGradeService {
    void saveGrades(List<ActivityGradeEntity> activityGradeEntity);

    List<ActivityGradeEntity> getGradesByActivityId(Integer activityId);

    List<ActivityGradeEntity> getGradesByStudentIdAndAssignationId(Integer studentId, Integer assignationId);
    Map<Integer, List<ActivityGradeEntity>> getGradesByAssignationId(Integer assignationId);
    void deleteAllGradesByActivityId(Integer activityId);
}
