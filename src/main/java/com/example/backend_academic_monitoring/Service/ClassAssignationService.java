package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;

import java.util.List;

public interface ClassAssignationService {
    List<ClassAssignationDTO> getClassAssignationByClassId(Integer classId);
    List<ClassAssignationDTO> getClassAssignationByClassroomId(Integer classroomId);
    List<ClassAssignationDTO> getClassAssignationByTeacherId(Integer teacherId);
    void createClassAssignation(AssignationCreateDTO classAssignationDTO);
    boolean existsByClassIdAndSubjectId(Integer classId, Integer subjectId);

}
