package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;

public class ClassAssignationMapper {
    public static AssignationCreateDTO toDTO(ClassAssignationEntity classAssignationEntity){
        AssignationCreateDTO classAssignationDTO = new AssignationCreateDTO();
        classAssignationDTO.setClassId(classAssignationEntity.getClassId());
        classAssignationDTO.setClassroomId(classAssignationEntity.getClassroomId());
        classAssignationDTO.setSubjectId(classAssignationEntity.getSubjectId());
        classAssignationDTO.setTeacherId(classAssignationEntity.getTeacherId());
        classAssignationDTO.setSchedule(classAssignationEntity.getSchedule());
        return classAssignationDTO;
    }
    public static ClassAssignationEntity toEntity(AssignationCreateDTO classAssignationDTO){
       ClassAssignationEntity classAssignationEntity = new ClassAssignationEntity();
         classAssignationEntity.setClassId(classAssignationDTO.getClassId());
         classAssignationEntity.setClassroomId(classAssignationDTO.getClassroomId());
         classAssignationEntity.setSubjectId(classAssignationDTO.getSubjectId());
         classAssignationEntity.setTeacherId(classAssignationDTO.getTeacherId());
         classAssignationEntity.setSchedule(classAssignationDTO.getSchedule());
         return classAssignationEntity;
    }
}
