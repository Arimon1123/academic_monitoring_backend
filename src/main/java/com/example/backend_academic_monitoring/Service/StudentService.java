package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentService {
    void saveStudent(StudentCreateDTO studentDTO);
    void updateStudent(StudentDTO studentDTO);
    void deleteStudent(Integer id);
    StudentDTO getStudent(Integer id);
    List<StudentDTO> getAllStudent();
    boolean existsByCi(String ci);
    boolean existsByRude(String rude);

}
