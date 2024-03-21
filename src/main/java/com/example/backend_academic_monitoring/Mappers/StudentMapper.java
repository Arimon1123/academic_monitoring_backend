package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

public class StudentMapper {
    public static StudentDTO toDTO(StudentEntity studentEntity) {
        StudentDTO student = new StudentDTO();
        student.setId(studentEntity.getId());
        student.setName(studentEntity.getName());
        student.setCi(studentEntity.getCi());
        student.setFatherLastname(studentEntity.getFatherLastname());
        student.setMotherLastname(studentEntity.getMotherLastname());
        student.setBirthDate(studentEntity.getBirthdate());
        student.setAddress(studentEntity.getAddress());
        student.setRude(studentEntity.getRude());
        return student;

    }
    public static StudentEntity toEntity(StudentCreateDTO studentDTO) {
        return new StudentEntity(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getCi(),
                studentDTO.getFatherLastname(),
                studentDTO.getMotherLastname(),
                studentDTO.getBirthDate(),
                studentDTO.getAddress(),
                1,
                studentDTO.getRude()
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
                1,
                studentDTO.getRude()
        );
    }
}
