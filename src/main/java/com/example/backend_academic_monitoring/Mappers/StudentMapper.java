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
        student.setEmail(studentEntity.getEmail());
        student.setParents(studentEntity.getParents().stream().map(ParentMapper::toDTO).toList());
        if (studentEntity.getUser() != null) {
            student.setUser(UserMapper.entityToDTO(studentEntity.getUser()));
        }
        return student;
    }

    public static StudentEntity toEntity(StudentCreateDTO studentDTO) {
        return getStudentEntity(studentDTO);

    }

    public static StudentEntity toEntity(StudentDTO studentDTO) {
        return getStudentEntity(studentDTO);
    }

    private static StudentEntity getStudentEntity(StudentDTO studentDTO) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDTO.getId());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setCi(studentDTO.getCi());
        studentEntity.setFatherLastname(studentDTO.getFatherLastname());
        studentEntity.setMotherLastname(studentDTO.getMotherLastname());
        studentEntity.setBirthdate(studentDTO.getBirthDate());
        studentEntity.setAddress(studentDTO.getAddress());
        studentEntity.setRude(studentDTO.getRude());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setStatus(1);
        return studentEntity;
    }
}
