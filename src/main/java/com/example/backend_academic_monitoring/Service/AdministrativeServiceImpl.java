package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import com.example.backend_academic_monitoring.Repository.AdministrativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrativeServiceImpl {
    private final AdministrativeRepository administrativeRepository;

    public AdministrativeServiceImpl(AdministrativeRepository administrativeRepository) {
        this.administrativeRepository = administrativeRepository;
    }

    public void saveAdministrative(AdministrativeDTO administrativeDTO, Integer userId) {
        AdministrativeEntity administrativeEntity = new AdministrativeEntity();
        administrativeEntity.setName(administrativeDTO.getName());
        administrativeEntity.setAddress(administrativeDTO.getAddress());
        administrativeEntity.setEmail(administrativeDTO.getEmail());
        administrativeEntity.setPhone(administrativeDTO.getPhone());
        administrativeEntity.setLastname(administrativeDTO.getLastname());
        administrativeEntity.setStatus(administrativeDTO.getStatus());
        administrativeEntity.setUserId(userId);
        administrativeEntity.setStatus(1);
        administrativeRepository.save(administrativeEntity);
    }

    public AdministrativeDTO findAdministrativeByUserId(Integer userId) {
        AdministrativeEntity administrativeEntity = administrativeRepository.findByUserId(userId);
        AdministrativeDTO administrativeDTO = new AdministrativeDTO();
        administrativeDTO.setName(administrativeEntity.getName());
        administrativeDTO.setLastname(administrativeEntity.getLastname());
        administrativeDTO.setAddress(administrativeEntity.getAddress());
        administrativeDTO.setEmail(administrativeEntity.getEmail());
        administrativeDTO.setPhone(administrativeEntity.getPhone());
        administrativeDTO.setStatus(administrativeEntity.getStatus());
        return administrativeDTO;
    }
}
