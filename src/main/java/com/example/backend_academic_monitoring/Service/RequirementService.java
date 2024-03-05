package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.RequirementEntity;

import java.util.List;

public interface RequirementService {
    public void saveRequirement(RequirementEntity requirementEntity);
    public void deleteRequirement(Integer id);
    public List<RequirementEntity> getRequirements();
}
