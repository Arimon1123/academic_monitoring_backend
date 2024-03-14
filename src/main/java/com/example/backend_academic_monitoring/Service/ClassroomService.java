package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.DTO.ClassroomDTO;

import java.util.List;

public interface ClassroomService {
    String getClassroomName(Integer classroomId);
    List<ClassroomDTO> getClassroomsByRequirement(List<Integer> requirements);
    List<ClassroomDTO> getAllClassrooms(List<Integer> requirements);

}
