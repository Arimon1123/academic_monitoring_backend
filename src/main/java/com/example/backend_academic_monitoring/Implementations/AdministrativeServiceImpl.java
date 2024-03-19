package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Mappers.AdministrativeMapper;
import com.example.backend_academic_monitoring.Repository.AdministrativeRepository;
import com.example.backend_academic_monitoring.Service.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrativeServiceImpl implements AdministrativeService {
    private final AdministrativeRepository administrativeRepository;

    @Autowired
    public AdministrativeServiceImpl(AdministrativeRepository administrativeRepository) {
        this.administrativeRepository = administrativeRepository;
    }

    @Override
    public void save(PersonEntity personEntity) {
        AdministrativeEntity administrativeEntity = new AdministrativeEntity();
        administrativeEntity.setPerson(personEntity);
        administrativeEntity.setStatus(1);
        administrativeRepository.save(administrativeEntity);

    }

    @Override
    public AdministrativeDTO findByUserId(Integer userId) {
        return AdministrativeMapper.toDTO(administrativeRepository.findByPerson_UserId(userId));
    }
}
