package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

public class StudentMapper {
    public static StudentDTO toDTO(StudentEntity studentEntity) {
        return new StudentDTO(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getCi(),
                studentEntity.getFatherLastname(),
                studentEntity.getMotherLastname(),
                studentEntity.getBirthdate(),
                studentEntity.getAddress()
        );
    }
    public static StudentEntity toEntity(StudentDTO studentDTO) {
        return new StudentEntity(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getCi(),
                studentDTO.getFatherLastname(),
                studentDTO.getMotherLastname(),
                studentDTO.getBirthDate(),
                studentDTO.getAddress(),
                1
        );
    }
}
