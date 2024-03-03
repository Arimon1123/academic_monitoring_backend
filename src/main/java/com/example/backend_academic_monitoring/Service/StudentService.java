package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentService {
    public void saveStudent(StudentCreateDTO studentDTO);
    public void updateStudent(StudentDTO studentDTO);
    public void deleteStudent(Integer id);
    public StudentDTO getStudent(Integer id);
    public List<StudentDTO> getAllStudent();

}
