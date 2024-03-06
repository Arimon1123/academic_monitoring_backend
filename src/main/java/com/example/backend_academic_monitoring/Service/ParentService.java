package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ParentDTO;
import com.example.backend_academic_monitoring.Entity.ParentEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

import java.util.List;

public interface ParentService {
    void save(PersonEntity personEntity);
    ParentEntity getParent(Integer id);
    List<ParentDTO> getParentByCi(String ci);
}
