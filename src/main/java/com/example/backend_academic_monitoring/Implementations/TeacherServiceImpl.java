package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import com.example.backend_academic_monitoring.Entity.TeacherSubjectEntity;
import com.example.backend_academic_monitoring.Mappers.TeacherMapper;
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

    @Override
    public List<TeacherDTO> getTeacherBySubject(Integer subjectId) {
        List<TeacherEntity> teachers = teacherRepository.findBySubjectId(subjectId);
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (TeacherEntity teacher : teachers) {
           teacherDTOS.add(TeacherMapper.toDTO(teacher));
        }
        return teacherDTOS;
    }

    @Override
    public String getTeacherName(Integer teacherId) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        return teacher.getPerson().getName() + " " + teacher.getPerson().getLastname();
    }

    @Override
    public TeacherDTO findTeacherByUserId(Integer userId) {
        return TeacherMapper.toDTO(teacherRepository.findByPerson_UserId(userId));
    }
}
