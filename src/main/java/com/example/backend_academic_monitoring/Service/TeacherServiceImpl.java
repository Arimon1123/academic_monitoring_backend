package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Repository.SubjectRepository;
import com.example.backend_academic_monitoring.Repository.TeacherRepository;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

    public TeacherServiceImpl(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void saveTeacher(TeacherDTO teacherDTO, Integer userId){
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName(teacherDTO.getName());
        teacherEntity.setLastname(teacherDTO.getLastname());
        teacherEntity.setAddress(teacherDTO.getAddress());
        teacherEntity.setPhone(teacherDTO.getPhone());
        teacherEntity.setEmail(teacherDTO.getEmail());
        teacherEntity.setStatus(1);
        teacherEntity.setUserId(userId);
        teacherRepository.save(teacherEntity);

    }

    @Override
    public void deleteTeacher(Integer id) {

    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO, List<SubjectEntity> teacherSubjectEntities) {

    }

    @Override
    public void getTeacher(Integer id) {

    }

    @Override
    public void getAllTeacher() {

    }

    @Override
    public TeacherDTO getTeacherByUserId(Integer userId) {
        LOGGER.info("getTeacherByUserId: {}", userId);
        TeacherEntity teacherEntity = teacherRepository.findByUserId(userId);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setName(teacherEntity.getName());
        teacherDTO.setLastname(teacherEntity.getLastname());
        teacherDTO.setAddress(teacherEntity.getAddress());
        teacherDTO.setPhone(teacherEntity.getPhone());
        teacherDTO.setEmail(teacherEntity.getEmail());
        return teacherDTO;
    }
}
