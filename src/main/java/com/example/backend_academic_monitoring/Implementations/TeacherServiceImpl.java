package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ConsultHourDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.ConsultHourEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import com.example.backend_academic_monitoring.Mappers.ConsultHourMapper;
import com.example.backend_academic_monitoring.Mappers.TeacherMapper;
import com.example.backend_academic_monitoring.Repository.TeacherRepository;
import com.example.backend_academic_monitoring.Service.SubjectService;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);
    private final TeacherRepository teacherRepository;
    private final SubjectService subjectService;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, SubjectService subjectService) {
        this.teacherRepository = teacherRepository;
        this.subjectService = subjectService;
    }

    @Override
    public void save(PersonEntity personEntity, String academicEmail, List<SubjectDTO> subjects, List<ConsultHourDTO> consultHours) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAcademicEmail(academicEmail);
        teacherEntity.setPerson(personEntity);
        teacherEntity.setStatus(1);
        if (subjects != null) {
            teacherEntity.setSubjects(subjects.stream().map(
                    subjectDTO -> subjectService.getById(subjectDTO.getId())
            ).toList());
        }
        if (consultHours != null) {
            teacherEntity.setConsultHours(consultHours.stream().map(
                    consultHourDTO -> ConsultHourMapper.toEntity(consultHourDTO, teacherEntity)
            ).toList());

        }
        teacherRepository.save(teacherEntity);
        log.info("subjects {}", subjects);

    }

    @Override
    public void saveTeacherSubjects(Integer teacherId, List<SubjectDTO> subjects) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        List<SubjectEntity> subjectsEntities = new ArrayList<>();
        for (SubjectDTO subject : subjects) {
            subjectsEntities.add(subjectService.getById(subject.getId()));
        }
        teacher.setSubjects(subjectsEntities);
        teacherRepository.save(teacher);
    }

    @Override
    public void update(TeacherEntity teacherEntity) {
        teacherRepository.save(teacherEntity);
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
    public TeacherDTO findTeacherDTOByUserId(Integer userId) {
        TeacherEntity teacher = teacherRepository.findByPerson_UserId(userId);
        return getTeacherDTO(teacher);
    }

    @Override
    public TeacherDTO findTeacherDTOById(Integer teacherId) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        return getTeacherDTO(teacher);
    }

    @Override
    public TeacherEntity findTeacherEntityByUserId(Integer userId) {
        return teacherRepository.findByPerson_UserId(userId);
    }

    @Override
    public void saveConsultHours(Integer teacherId, List<ConsultHourDTO> consultHours) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        List<ConsultHourEntity> consultHourEntities = new ArrayList<>();
        for (ConsultHourDTO consultHour : consultHours) {
            ConsultHourEntity consultHourEntity = ConsultHourMapper.toEntity(consultHour, teacher);
            consultHourEntity.setTeacherEntity(teacher);
            consultHourEntities.add(consultHourEntity);
        }
        teacher.setConsultHours(consultHourEntities);
        teacherRepository.save(teacher);
    }

    @Override
    public List<ConsultHourDTO> getConsultHours(Integer teacherId) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        return teacher.getConsultHours().stream().map(ConsultHourMapper::toDTO).toList();
    }

    @Override
    public boolean existAcademicEmail(String academicEmail) {
        return teacherRepository.existsByAcademicEmail(academicEmail);
    }

    @Override
    public void updateAcademicEmail(String academicEmail, Integer teacherId) {
        TeacherEntity teacher = teacherRepository.getReferenceById(teacherId);
        teacher.setAcademicEmail(academicEmail);
        teacherRepository.save(teacher);
    }

    private TeacherDTO getTeacherDTO(TeacherEntity teacher) {
        TeacherDTO teacherDTO = TeacherMapper.toDTO(teacher);
        ArrayList<SubjectDTO> subjects = new ArrayList<>();
        for (SubjectEntity subject : teacher.getSubjects()) {
            subjects.add(subjectService.getDTOById(subject.getId()));
        }
        log.info("subjectsDTO {}", subjects);
        teacherDTO.setSubjects(subjects);
        return teacherDTO;
    }
}
