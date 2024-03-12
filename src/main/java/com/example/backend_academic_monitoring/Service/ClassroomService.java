package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ClassSubjectDTO;
import com.example.backend_academic_monitoring.DTO.ClassroomDTO;

import java.util.List;

public interface ClassroomService {
    List<ClassroomDTO> getClassroomsByRequirement(List<Integer> requirements);
    String getClassroomName(Integer classroomId);
    List<ClassSubjectDTO> getClassroomSubjects(Integer classroomId);
}
