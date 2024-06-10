package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ClassDTO;
import com.example.backend_academic_monitoring.DTO.ClassListDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

import java.util.List;

public interface ClassService {
    ClassEntity getClass(Integer classId);

    void addStudentToClass(Integer classId, StudentEntity student);

    List<ClassListDTO> getClassByGradeAndYearAndShift(Integer gradeId, Integer year, Integer shift);

    String getClassName(Integer classId);

    ClassEntity getClassByStudentId(Integer studentId);

    ClassEntity getClassByAssignationId(Integer assignationId);

    List<ClassEntity> getClassByGradeAndYear(Integer gradeId, Integer year);

    boolean removeStudentFromClass(ClassEntity classEntity, StudentEntity student);

    void saveClass(ClassDTO classDTO);

    List<ClassEntity> getStudentClasses(Integer studentId);

    void setApprovalStatus(Integer studentId, Integer classId, Integer approvalStatus);

    ClassListDTO getClassDTOByStudentIdAndYear(Integer studentId, Integer year);
}