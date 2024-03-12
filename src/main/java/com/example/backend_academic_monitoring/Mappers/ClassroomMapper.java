package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.ClassroomDTO;
import com.example.backend_academic_monitoring.Entity.ClassroomEntity;

public class ClassroomMapper {
    public static ClassroomDTO toDTO (ClassroomEntity classroomEntity) {
        ClassroomDTO classroom = new ClassroomDTO();
        classroom.setId(classroomEntity.getId());
        classroom.setBlock(classroomEntity.getBlock());
        classroom.setType(classroomEntity.getType());
        classroom.setNumber(classroomEntity.getNumber());
        return classroom;
    }
}
