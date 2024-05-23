package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.ConsultHourDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.TeacherEntity;

import java.util.List;

public interface TeacherService {
    void save(PersonEntity personEntity, String academicEmail, List<SubjectDTO> subjects, List<ConsultHourDTO> consultoHours);

    void saveTeacherSubjects(Integer teacherId, List<SubjectDTO> subjects);

    void update(TeacherEntity teacherEntity);

    List<TeacherDTO> getTeacherBySubject(Integer subjectId);

    String getTeacherName(Integer teacherId);

    TeacherDTO findTeacherDTOByUserId(Integer userId);

    TeacherDTO findTeacherDTOById(Integer teacherId);

    TeacherEntity findTeacherEntityByUserId(Integer teacherId);

    void saveConsultHours(Integer teacherId, List<ConsultHourDTO> consultHours);

    List<ConsultHourDTO> getConsultHours(Integer teacherId);

    boolean existAcademicEmail(String academicEmail);

    void updateAcademicEmail(String academicEmail, Integer teacherId);

    TeacherDTO getTeacherByAssignationId(Integer assignationId);
}
