package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Repository.ActivityGradeRepository;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityGradeServiceImpl implements ActivityGradeService {
    private final ActivityGradeRepository activityGradeRepository;

    public ActivityGradeServiceImpl(ActivityGradeRepository activityGradeRepository) {
        this.activityGradeRepository = activityGradeRepository;
    }


    @Override
    public void saveGrades(List<ActivityGradeEntity> activityGradeEntity) {
        activityGradeRepository.saveAll(activityGradeEntity);
    }

    @Override
    public List<ActivityGradeEntity> getGradesByActivityId(Integer activityId) {
        return activityGradeRepository.findAllByActivityId(activityId);
    }

    @Override
    public List<ActivityGradeEntity> getGradesByStudentIdAndAssignationId(Integer studentId, Integer assignationId) {
        return activityGradeRepository.findAllByStudentIdAndAssignationId(studentId, assignationId);
    }


}
