package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.Entity.ParentEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

import java.util.List;

public interface ParentService {
    void save(PersonEntity personEntity);

    void update(ParentEntity parent);

    ParentEntity getParent(Integer id);

    List<ParentDTO> getParentByCi(String ci);

    ParentDTO getParentDTOById(Integer id);

    ParentDTO getParentDTOByUserId(Integer id);

    ParentEntity getParentEntityByUserId(Integer userId);

    List<ParentDTO> getParentsByStudentId(Integer id);
}
