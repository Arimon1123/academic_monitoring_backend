package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import com.example.backend_academic_monitoring.Repository.TeacherRepository;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void save(PersonEntity personEntity, String academicEmail) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAcademicEmail(academicEmail);
        teacherEntity.setPerson(personEntity);
        teacherEntity.setStatus(1);
        teacherRepository.save(teacherEntity);

    }
}
