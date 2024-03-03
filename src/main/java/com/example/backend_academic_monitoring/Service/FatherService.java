package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.FatherEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

public interface FatherService {
    public void save(PersonEntity personEntity);
    public FatherEntity getFather(Integer id);
}
