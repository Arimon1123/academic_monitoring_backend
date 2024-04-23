package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.Entity.ParentEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Mappers.ParentMapper;
import com.example.backend_academic_monitoring.Repository.ParentRepository;
import com.example.backend_academic_monitoring.Service.ParentService;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository, PersonService personService) {
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
    public ParentEntity getParent(Integer id) {
        return parentRepository.getReferenceById(id);
    }

    @Override
    public ParentDTO getParentDTOById(Integer id) {
        return ParentMapper.toFatherDTO(getParent(id));
    }

    @Override
    public List<ParentDTO> getParentByCi(String ci) {
        List<ParentEntity> fatherEntities = parentRepository.findAllByPersonCiContainingAndStatus(ci, 1);
        return fatherEntities.stream().map(ParentMapper::toFatherDTO).toList();
    }

    @Override
    public ParentDTO getParentByUserId(Integer id) {
        return ParentMapper.toFatherDTO(parentRepository.findByPerson_UserId(id));
    }

}
