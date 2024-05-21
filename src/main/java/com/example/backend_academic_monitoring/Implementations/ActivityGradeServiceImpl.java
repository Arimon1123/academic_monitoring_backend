package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Repository.ActivityGradeRepository;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivityGradeServiceImpl implements ActivityGradeService {
    private final ActivityGradeRepository activityGradeRepository;

    public ActivityGradeServiceImpl(ActivityGradeRepository activityGradeRepository) {
        this.activityGradeRepository = activityGradeRepository;
    }

    @Override
    public void saveGrades(List<ActivityGradeEntity> activityGradeEntity) {
        activityGradeEntity = activityGradeEntity.stream().peek(
                grade -> grade.setStatus(1)
        ).collect(Collectors.toList());
        activityGradeRepository.saveAll(activityGradeEntity);
    }

    @Override
    public List<ActivityGradeEntity> getGradesByActivityId(Integer activityId) {
        return activityGradeRepository.findAllByActivityId(activityId);
    }

    @Override
    public List<ActivityGradeEntity> getGradesByStudentIdAndAssignationId(Integer assignationId, Integer studentId, Integer bimester) {
        return activityGradeRepository.findAllByStudentIdAndAssignationId(studentId, assignationId, bimester);
    }

    @Override
    public Map<Integer, List<ActivityGradeEntity>> getGradesByAssignationIdAndBimester(Integer assignationId, Integer bimester) {
        List<ActivityGradeEntity> activityGradeEntities = activityGradeRepository.findAllByAssignationIdAndBimester(assignationId, bimester);
        return activityGradeEntities.stream().collect(Collectors.groupingBy(ActivityGradeEntity::getStudentId));
    }

    @Override
    public void deleteAllGradesByActivityId(Integer activityId) {
        List<ActivityGradeEntity> activityGradeEntities = activityGradeRepository.findAllByActivityId(activityId);
        activityGradeRepository.deleteAllInBatch(activityGradeEntities);
    }

    @Override
    public List<ActivityGradeEntity> activityGradesByStudentAndYear(Integer studentId, Integer year) {
        return activityGradeRepository.findByStudentIdAndClassId(studentId, year);
    }

    @Override
    public void updateGrades(List<ActivityGradeEntity> activityGradeEntities) {
        activityGradeRepository.saveAll(activityGradeEntities);
    }


}
