package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.FatherStudentEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Mappers.StudentMapper;
import com.example.backend_academic_monitoring.Repository.FatherStudentRepository;
import com.example.backend_academic_monitoring.Repository.StudentRepository;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FatherStudentRepository fatherStudentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, FatherStudentRepository fatherStudentRepository) {
        this.studentRepository = studentRepository;
        this.fatherStudentRepository = fatherStudentRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveStudent(StudentDTO studentDTO, PersonDTO fatherDTO){
        StudentEntity studentEntity = StudentMapper.toEntity(studentDTO);
        studentEntity = studentRepository.save(studentEntity);
        FatherStudentEntity fatherStudentEntity = new FatherStudentEntity();
        fatherStudentEntity.setFather(PersonMapper.dtoToEntity(fatherDTO,null,1));
        fatherStudentEntity.setStudent(studentEntity);
        fatherStudentRepository.save(fatherStudentEntity);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        if(studentDTO.getId() == null) throw new RuntimeException("Id is required");
        StudentEntity studentEntity = StudentMapper.toEntity(studentDTO);
        studentRepository.save(studentEntity);
    }

    @Override
    public void deleteStudent(Integer id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        studentEntity.setStatus(0);
        studentRepository.save(studentEntity);
    }

    @Override
    public StudentDTO getStudent(Integer id) {
        StudentEntity studentEntity = studentRepository.findByIdAndStatus(id,1);
        if(studentEntity == null) throw new RuntimeException("Student not found");
        return StudentMapper.toDTO(studentEntity);
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentEntity> studentEntities = studentRepository.findAllByStatus(1);
        return studentEntities.stream().map(StudentMapper::toDTO).toList();
    }
}
