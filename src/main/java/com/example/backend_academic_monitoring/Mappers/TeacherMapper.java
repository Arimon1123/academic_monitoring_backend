package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;

public class TeacherMapper{
    public static TeacherDTO  toDTO (TeacherEntity teacherEntity) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacherEntity.getId());
        teacherDTO.setPerson(PersonMapper.entityToDTO(teacherEntity.getPerson()));
        teacherDTO.setAcademicEmail(teacherEntity.getAcademicEmail());
        return teacherDTO;
    }
}
