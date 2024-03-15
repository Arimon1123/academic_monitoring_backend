package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;

public class ClassAssignationMapper {
    public static AssignationCreateDTO toDTO(ClassAssignationEntity classAssignationEntity){
        return new AssignationCreateDTO(
                classAssignationEntity.getSubjectId(),
                classAssignationEntity.getClassId(),
                classAssignationEntity.getTeacherId(),
                classAssignationEntity.getClassroomId(),
                classAssignationEntity.getSchedule()
        );
    }
    public static ClassAssignationEntity toEntity(AssignationCreateDTO classAssignationDTO){
        return new ClassAssignationEntity(
                null,
                classAssignationDTO.getClassId(),
                classAssignationDTO.getClassroomId(),
                classAssignationDTO.getSubjectId(),
                classAssignationDTO.getTeacherId(),
                classAssignationDTO.getSchedule()
        );
    }
}
