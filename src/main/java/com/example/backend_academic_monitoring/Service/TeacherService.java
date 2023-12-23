package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import java.util.List;


public interface TeacherService {
    public void saveTeacher(PersonDTO teacherDTO, Integer userId);
    public void deleteTeacher(Integer id);
    public void updateTeacher(PersonDTO teacherDTO);
    public void getTeacher(Integer id);
    public void getAllTeacher();
    public TeacherDTO getTeacherByUserId(Integer userId);

}
