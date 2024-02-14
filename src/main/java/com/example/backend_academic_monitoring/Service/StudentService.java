package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;

import java.util.List;

public interface StudentService {
    public void saveStudent(StudentDTO studentDTO, PersonDTO fatherDTO);
    public void updateStudent(StudentDTO studentDTO);
    public void deleteStudent(Integer id);
    public StudentDTO getStudent(Integer id);
    public List<StudentDTO> getAllStudent();

}
