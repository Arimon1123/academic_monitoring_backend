package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.Entity.ParentEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Mappers.ParentMapper;
import com.example.backend_academic_monitoring.Repository.ParentRepository;
import com.example.backend_academic_monitoring.Service.ParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    private static final Logger log = LoggerFactory.getLogger(ParentServiceImpl.class);
    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public void save(PersonEntity personEntity) {
        ParentEntity parentEntity = new ParentEntity();
        parentEntity.setPerson(personEntity);
        parentEntity.setStatus(1);
        parentRepository.save(parentEntity);

    }

    @Override
    public void update(ParentEntity parent) {
        parentRepository.save(parent);
    }

    @Override
    public ParentEntity getParent(Integer id) {
        log.info("Buscando padre con id: {}", id);
        return parentRepository.getReferenceById(id);
    }

    @Override
    public ParentDTO getParentDTOById(Integer id) {
        return ParentMapper.toDTO(getParent(id));
    }

    @Override
    public List<ParentDTO> getParentByCi(String ci) {
        List<ParentEntity> fatherEntities = parentRepository.findAllByPersonCiContainingAndStatus(ci, 1);
        return fatherEntities.stream().map(ParentMapper::toDTO).toList();
    }

    @Override
    public ParentDTO getParentDTOByUserId(Integer id) {
        return ParentMapper.toDTO(parentRepository.findByPerson_UserId(id));
    }

    @Override
    public ParentEntity getParentEntityByUserId(Integer userId) {
        return parentRepository.findByPerson_UserId(userId);
    }

    @Override
    public List<ParentDTO> getParentsByStudentId(Integer id) {
        List<ParentEntity> parentEntities = parentRepository.findParentEntityByStudentId(id);
        return parentEntities.stream().map(ParentMapper::toDTO).toList();
    }

}
