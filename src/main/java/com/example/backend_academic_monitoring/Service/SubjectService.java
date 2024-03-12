package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.SubjectCreateDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;

import java.util.List;

public interface SubjectService {
    void save(SubjectCreateDTO subjectEntity);
    void update(SubjectDTO subjectEntity);
    void delete(Integer id);
    SubjectEntity getById(Integer id);
    List<SubjectDTO> getAll();
    List<SubjectDTO> getByGrade(Integer gradeId);
    List<SubjectDTO> getByTeacher(Integer teacherId);
    String getSubjectName(Integer subjectId);
}
