package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.SubjectCreateDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Entity.GradeEntity;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Mappers.SubjectMapper;
import com.example.backend_academic_monitoring.Repository.SubjectRepository;
import com.example.backend_academic_monitoring.Service.GradeService;
import com.example.backend_academic_monitoring.Service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final GradeService gradeService;
    public static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, GradeService gradeService) {
        this.subjectRepository = subjectRepository;
        this.gradeService = gradeService;
    }

    @Override
    public void save(SubjectCreateDTO subjectDTO){
        GradeEntity gradeEntity = gradeService.getById(subjectDTO.getGradeId());
        SubjectEntity subjectEntity = SubjectMapper.toEntity(subjectDTO, gradeEntity);
        subjectEntity.setStatus(1);
        LOGGER.info("Saving subject {}", subjectEntity);
        subjectRepository.save(subjectEntity);
    }

    @Override
    public void update(SubjectDTO subjectDTO) {
        SubjectEntity subjectEntity = subjectRepository.getReferenceById(subjectDTO.getId());
        subjectEntity.setName(subjectDTO.getName());
        subjectEntity.setHours(subjectDTO.getHours());
        subjectRepository.save(subjectEntity);

    }

    @Override
    public void delete(Integer id) {
        SubjectEntity subjectEntity = subjectRepository.getReferenceById(id);
        subjectEntity.setStatus(0);
        subjectRepository.save(subjectEntity);
    }

    @Override
    public SubjectEntity getById(Integer id) {
        return subjectRepository.getReferenceById(id);
    }

    @Override
    public List<SubjectDTO> getAll() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAllByStatus(1);
        return subjectEntities.stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> getByGrade(Integer gradeId) {
        List<SubjectEntity> subjectEntities = subjectRepository.findAllByGradeIdAndStatus(gradeId, 1);
        return subjectEntities.stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> getByTeacher(Integer teacherId) {
        List<SubjectEntity> subjectEntities = subjectRepository.findAllByTeacher(teacherId);
        return subjectEntities.stream().map(SubjectMapper::toDTO).collect(Collectors.toList());
    }
}
