package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    void saveStudent(StudentCreateDTO studentDTO, UserEntity userEntity);

    void updateStudent(StudentDTO studentDTO);

    void deleteStudent(Integer id);

    StudentDTO getStudent(Integer id);

    List<StudentDTO> getAllStudent();

    boolean existsByCi(String ci);

    boolean existsByRude(String rude);

    boolean existsByEmail(String email);

    List<StudentDTO> findAllByParentId(Integer parentId);

    List<StudentDTO> findAllByClassId(Integer classId);

    List<StudentDTO> findAllByAssignationId(Integer assignationId);

    Page<StudentDTO> searchStudent(String ci, String rude, String name, String lastname, Integer page, Integer size);

    void updateStudentParents(Integer studentId, List<Integer> parentsId);

    StudentEntity getStudentEntity(Integer id);

    void updateStudentClass(Integer studentId, Integer classId);

    StudentDTO findByUserId(Integer userId);

    void setApprovalStatus(Integer studentId, Integer approvalStatus);

    List<StudentEntity> getAllStudentsByYear(Integer year);
}
