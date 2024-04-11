package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;

import java.util.List;

public interface ClassAssignationService {
    List<ClassAssignationDTO> getClassAssignationByClassId(Integer classId);

    List<ClassAssignationDTO> getClassAssignationByClassroomId(Integer classroomId);

    List<ClassAssignationDTO> getClassAssignationByTeacherId(Integer teacherId);

    void createClassAssignation(AssignationCreateDTO classAssignationDTO);

    ClassAssignationEntity getClassAssignationByClassIdAndSubjectId(Integer classId, Integer subjectId);

    List<ClassAssignationDTO> getClassAssignationByStudentAndYear(Integer studentId, Integer year);

    ClassAssignationDTO getClassAssignationById(Integer id);

}
