package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;

import java.util.List;
import java.util.Map;

public interface ActivityGradeService {
    void saveGrades(List<ActivityGradeEntity> activityGradeEntity);

    List<ActivityGradeEntity> getGradesByActivityId(Integer activityId);

    List<ActivityGradeEntity> getGradesByStudentIdAndAssignationId(Integer studentId, Integer assignationId, Integer bimester);

    Map<Integer, List<ActivityGradeEntity>> getGradesByAssignationIdAndBimester(Integer assignationId, Integer bimester);

    void deleteAllGradesByActivityId(Integer activityId);

    List<ActivityGradeEntity> activityGradesByStudentAndYear(Integer studentId, Integer year);

    void updateGrades(List<ActivityGradeEntity> activityGradeEntities);
}
