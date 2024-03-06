package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.Entity.ParentEntity;

public class ParentMapper {
    public static ParentDTO toFatherDTO(ParentEntity father){
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setParentId(father.getId());
        parentDTO.setPerson(PersonMapper.entityToDTO(father.getPerson()));
        parentDTO.setStatus(father.getStatus());
        return parentDTO;
    }
}
