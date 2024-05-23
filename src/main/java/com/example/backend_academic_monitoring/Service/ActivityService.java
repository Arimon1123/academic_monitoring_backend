package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ActivityEntity;

import java.util.List;

public interface ActivityService {
    void saveActivity(ActivityEntity activityCreateDTO);

    void updateActivity(ActivityEntity activityCreateDTO);

    List<ActivityEntity> findActivitiesByAssignationIdAndBimester(Integer assignationId, Integer bimester);

    void deleteActivity(Integer id);

    ActivityEntity findActivityById(Integer id);
}
