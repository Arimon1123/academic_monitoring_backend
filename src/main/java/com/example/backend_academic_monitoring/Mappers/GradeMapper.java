package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.Entity.GradeEntity;

public class GradeMapper {
    public static GradeDTO toGradeDTO(GradeEntity gradeEntity) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(gradeEntity.getId());
        gradeDTO.setSection(gradeEntity.getSection());
        gradeDTO.setNumber(gradeEntity.getNumber());
        return gradeDTO;
    }
}
