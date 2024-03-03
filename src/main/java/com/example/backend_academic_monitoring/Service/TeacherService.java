package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;


import java.util.List;

public interface TeacherService {
    public void save(PersonEntity personEntity, String academicEmail);
    public void saveTeacherSubjects(Integer teacherId, List<SubjectDTO> subjects);
}
