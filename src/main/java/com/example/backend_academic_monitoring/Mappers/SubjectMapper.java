package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Entity.GradeEntity;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Utilities.GradeName;

public class SubjectMapper {
    public static SubjectEntity toEntity(SubjectDTO subjectDTO, GradeEntity gradeEntity) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setId(subjectDTO.getId());
        subjectEntity.setName(subjectDTO.getName());
        subjectEntity.setHours(subjectDTO.getHours());
        subjectEntity.setStatus(subjectDTO.getStatus());
        subjectEntity.setGrade(gradeEntity);
        subjectEntity.setRequirements(subjectDTO.getRequirements());
        return subjectEntity;
    }
    public static SubjectDTO toDTO(SubjectEntity subjectEntity) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subjectEntity.getId());
        subjectDTO.setName(subjectEntity.getName());
        subjectDTO.setHours(subjectEntity.getHours());
        subjectDTO.setStatus(subjectEntity.getStatus());
        subjectDTO.setSection(subjectEntity.getGrade().getSection());
        subjectDTO.setGradeName(GradeName.gradeNames.get(Integer.parseInt(subjectEntity.getGrade().getNumber())));
        subjectDTO.setRequirements(subjectEntity.getRequirements());
        return subjectDTO;
    }
}
