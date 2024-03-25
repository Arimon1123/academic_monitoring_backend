package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ActivityEntity;
import com.example.backend_academic_monitoring.Repository.ActivityRepository;
import com.example.backend_academic_monitoring.Service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void saveActivity(ActivityEntity activityCreateDTO) {
        activityRepository.save(activityCreateDTO);

    }

    @Override
    public void updateActivity(ActivityEntity activityCreateDTO) {
        ActivityEntity activityEntity = activityRepository.getReferenceById(activityCreateDTO.getId());
        activityEntity.setBimester(activityCreateDTO.getBimester());
        activityEntity.setName(activityCreateDTO.getName());
        activityEntity.setValue(activityCreateDTO.getValue());
        activityEntity.setDimension(activityCreateDTO.getDimension());
        activityEntity.setStatus(activityCreateDTO.getStatus());
    }

    @Override
    public List<ActivityEntity> findActivitiesByAssignationId(Integer assignationId) {
        return activityRepository.findAllByAssignationId(assignationId);
    }
}
