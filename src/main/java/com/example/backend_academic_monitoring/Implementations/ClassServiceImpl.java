package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import com.example.backend_academic_monitoring.Repository.ClassRepository;
import com.example.backend_academic_monitoring.Service.ClassService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    @Override
    public ClassEntity getClass(Integer classId) {
        return classRepository.getReferenceById(classId);
    }

    @Override
    public void addStudentToClass(Integer classId, StudentEntity student) {
        ClassEntity classEntity = classRepository.getReferenceById(classId);
        classEntity.getStudents().add(student);
        classRepository.save(classEntity);
    }
}
