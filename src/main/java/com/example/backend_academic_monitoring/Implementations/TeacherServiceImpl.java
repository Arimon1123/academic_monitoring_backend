package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import com.example.backend_academic_monitoring.Entity.TeacherSubjectEntity;
import com.example.backend_academic_monitoring.Repository.TeacherRepository;
import com.example.backend_academic_monitoring.Repository.TeacherSubjectRepository;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherSubjectRepository teacherSubjectRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherSubjectRepository = teacherSubjectRepository;
    }

    @Override
    public void save(PersonEntity personEntity, String academicEmail) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAcademicEmail(academicEmail);
        teacherEntity.setPerson(personEntity);
        teacherEntity.setStatus(1);
        teacherRepository.save(teacherEntity);

    }

    @Override
    public void saveTeacherSubjects(Integer teacherId, List<SubjectDTO> subjects) {
        List<TeacherSubjectEntity> teacherSubjectList = new ArrayList<>();
        for (SubjectDTO subject : subjects) {
            TeacherSubjectEntity teacherSubjectEntity = new TeacherSubjectEntity();
            teacherSubjectEntity.setTeacherId(teacherId);
            teacherSubjectEntity.setSubjectId(subject.getId());
            teacherSubjectList.add(teacherSubjectEntity);
        }
        teacherSubjectRepository.saveAll(teacherSubjectList);
    }
}
