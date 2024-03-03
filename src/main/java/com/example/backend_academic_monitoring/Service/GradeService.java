package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.Entity.GradeEntity;

import java.util.List;

public interface GradeService {
    void save(GradeDTO gradeEntity);
    void update(GradeDTO gradeEntity);
    void delete(Integer id);
    List<GradeDTO> getAll();
    GradeEntity getById(Integer id);
    List<GradeDTO> getByTeacher(Integer teacherId);

}
