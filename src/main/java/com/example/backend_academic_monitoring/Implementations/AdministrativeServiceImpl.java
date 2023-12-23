package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import com.example.backend_academic_monitoring.Repository.AdministrativeRepository;
import com.example.backend_academic_monitoring.Service.AdministrativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrativeServiceImpl implements AdministrativeService {
    private final AdministrativeRepository administrativeRepository;

    public AdministrativeServiceImpl(AdministrativeRepository administrativeRepository) {
        this.administrativeRepository = administrativeRepository;
    }
    @Override
    public void saveAdministrative(PersonDTO administrativeDTO, Integer userId) {
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

    @Override
    public void deleteAdministrative(Integer id) {
        AdministrativeEntity administrativeEntity = administrativeRepository.findByUserId(id);
        administrativeEntity.setStatus(0);
        administrativeRepository.save(administrativeEntity);
    }

    @Override
    public void updateAdministrative(PersonDTO administrativeDTO) {
        AdministrativeEntity administrativeEntity = administrativeRepository.findByUserId(administrativeDTO.getId());
        administrativeEntity.setName(administrativeDTO.getName());
        administrativeEntity.setLastname(administrativeDTO.getLastname());
        administrativeEntity.setAddress(administrativeDTO.getAddress());
        administrativeEntity.setPhone(administrativeDTO.getPhone());
        administrativeEntity.setEmail(administrativeDTO.getEmail());
        administrativeRepository.save(administrativeEntity);
    }

    @Override
    public AdministrativeDTO getAdministrative(Integer id) {
        return null;
    }


    @Override
    public void getAllAdministrative() {

    }
    @Override
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
