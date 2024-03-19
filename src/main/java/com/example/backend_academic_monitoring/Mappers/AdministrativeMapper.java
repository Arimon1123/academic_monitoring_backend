package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

public class AdministrativeMapper {
    public static AdministrativeDTO toDTO (AdministrativeEntity administrativeEntity){
        AdministrativeDTO administrativeDTO = new AdministrativeDTO();
        administrativeDTO.setId(administrativeEntity.getId());
        administrativeDTO.setStatus(administrativeEntity.getStatus());
        administrativeDTO.setPerson(PersonMapper.entityToDTO(administrativeEntity.getPerson()));
        return administrativeDTO;
    }
}
