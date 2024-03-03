package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

import java.util.List;

public interface ClassService {
    public ClassEntity getClass(Integer classId);
    public void addStudentToClass(Integer classId, StudentEntity student);
}