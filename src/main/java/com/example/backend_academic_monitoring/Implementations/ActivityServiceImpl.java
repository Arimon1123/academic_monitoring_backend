package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ActivityEntity;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Repository.ActivityRepository;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import com.example.backend_academic_monitoring.Service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);

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
        activityRepository.save(activityEntity);
    }

    @Override
    public List<ActivityEntity> findActivitiesByAssignationIdAndBimester(Integer assignationId, Integer bimester) {
        LOGGER.info("assignation {} , bimester, {} " , assignationId, bimester);
        return activityRepository.findAllByAssignationIdAndStatusAndBimesterOrderById(assignationId,1,bimester);
    }

    @Override
    public void deleteActivity(Integer id) {
        ActivityEntity activity = activityRepository.getReferenceById(id);
        activity.setStatus(0);
        activityRepository.save(activity);
    }
}
