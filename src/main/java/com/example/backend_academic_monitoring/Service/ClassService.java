package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ClassListDTO;
import com.example.backend_academic_monitoring.DTO.ClassSubjectDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Entity.ClassSubjectEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

import java.util.List;

public interface ClassService {
    ClassEntity getClass(Integer classId);
    void addStudentToClass(Integer classId, StudentEntity student);
    List<ClassListDTO> getClassByGradeAndYearAndShift(Integer gradeId, Integer year, Integer shift);
    List<ClassSubjectDTO> getClassSubjects(Integer classId);
    String getClassName(Integer classId);
}