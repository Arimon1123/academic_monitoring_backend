package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;


import java.util.List;

public interface TeacherService {
    void save(PersonEntity personEntity, String academicEmail);
    void saveTeacherSubjects(Integer teacherId, List<SubjectDTO> subjects);
    List<TeacherDTO> getTeacherBySubject(Integer subjectId);
    String getTeacherName(Integer teacherId);
}
