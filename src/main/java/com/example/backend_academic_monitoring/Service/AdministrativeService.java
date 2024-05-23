package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

public interface AdministrativeService {
    void save(PersonEntity personEntity);

    void update(AdministrativeEntity administrative);

    AdministrativeDTO findDTOByUserId(Integer userId);

    AdministrativeEntity findEntityByUserId(Integer userId);

}
