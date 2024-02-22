package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.PersonEntity;

public interface TeacherService {
    public void save(PersonEntity personEntity, String academicEmail);
}
