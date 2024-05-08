package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.StudentEntity;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentCreateDTO studentDTO);

    void updateStudent(StudentDTO studentDTO);

    void deleteStudent(Integer id);

    StudentDTO getStudent(Integer id);

    List<StudentDTO> getAllStudent();

    boolean existsByCi(String ci);

    boolean existsByRude(String rude);

    List<StudentDTO> findAllByParentId(Integer parentId);

    List<StudentDTO> findAllByClassId(Integer classId);

    List<StudentDTO> findAllByAssignationId(Integer assignationId);

    List<StudentDTO> searchStudent(String ci, String rude, String name, String fatherLastname, String motherLastname);

    void updateStudentParents(Integer studentId, List<Integer> parentsId);

    StudentEntity getStudentEntity(Integer id);

    void updateStudentClass(Integer studentId, Integer classId);
}
