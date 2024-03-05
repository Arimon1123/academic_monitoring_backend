package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.RequirementEntity;
import com.example.backend_academic_monitoring.Repository.RequirementRepository;
import com.example.backend_academic_monitoring.Service.RequirementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementeServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;

    public RequirementeServiceImpl(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public void saveRequirement(RequirementEntity requirementEntity) {

    }

    @Override
    public void deleteRequirement(Integer id) {

    }

    @Override
    public List<RequirementEntity> getRequirements() {
        return requirementRepository.findAll();
    }
}
