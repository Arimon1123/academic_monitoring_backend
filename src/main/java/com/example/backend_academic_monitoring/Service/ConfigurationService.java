package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.UnfinishedSubjectDTO;
import com.example.backend_academic_monitoring.Entity.ConfigEntity;

import java.util.List;

public interface ConfigurationService {
    List<UnfinishedSubjectDTO> finishBimester();

    void finishYear();

    ConfigEntity getCurrentConfig();
}
