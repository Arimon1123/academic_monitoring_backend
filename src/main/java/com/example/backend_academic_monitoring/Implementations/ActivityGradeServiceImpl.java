package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import com.example.backend_academic_monitoring.Repository.ActivityGradeRepository;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import com.example.backend_academic_monitoring.Service.StudentService;
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

    @Override
    public Map<Integer,List<ActivityGradeEntity>> getGradesByAssignationId(Integer assignationId) {
        List<ActivityGradeEntity> activityGradeEntities = activityGradeRepository.findAllByAssignationId(assignationId);
        return activityGradeEntities.stream().collect(Collectors.groupingBy(ActivityGradeEntity::getStudentId));
    }

    @Override
    public void deleteAllGradesByActivityId(Integer activityId) {
        List<ActivityGradeEntity> activityGradeEntities = activityGradeRepository.findAllByActivityId(activityId);
        activityGradeRepository.deleteAllInBatch(activityGradeEntities);
    }


}
