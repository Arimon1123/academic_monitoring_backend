package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.*;
import com.example.backend_academic_monitoring.Mappers.StudentMapper;
import com.example.backend_academic_monitoring.Repository.ParentStudentRepository;
import com.example.backend_academic_monitoring.Repository.StudentRepository;
import com.example.backend_academic_monitoring.Service.ClassService;
import com.example.backend_academic_monitoring.Service.ParentService;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ParentStudentRepository parentStudentRepository;
    private final ParentService parentService;
    private final ClassService classService;
    public static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ParentStudentRepository parentStudentRepository, ParentService parentService, ClassService classService) {
        this.studentRepository = studentRepository;
        this.parentStudentRepository = parentStudentRepository;
        this.parentService = parentService;
        this.classService = classService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveStudent(StudentCreateDTO studentDTO){
        if(studentRepository.existsByCi(studentDTO.getCi())) throw new RuntimeException("Ci ya existe");
        if(studentRepository.existsByRude(studentDTO.getRude())) throw new RuntimeException("Rude ya existe");
        StudentEntity studentEntity = StudentMapper.toEntity(studentDTO);
        studentEntity = studentRepository.save(studentEntity);
        for(Integer parentId : studentDTO.getParentId()){
            ParentEntity parentEntity = parentService.getParent(parentId);
            ParentStudentEntity fatherStudentEntity = new ParentStudentEntity();
            fatherStudentEntity.setFather(parentEntity);
            fatherStudentEntity.setStudent(studentEntity);
            parentStudentRepository.save(fatherStudentEntity);
        }
        classService.addStudentToClass(studentDTO.getClassId(), studentEntity);
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
        ClassEntity classEntity = classService.getClassByStudentId(id);
        StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
        studentDTO.setStudentClass(classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier());
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentEntity> studentEntities = studentRepository.findAllByStatus(1);
        return studentEntities.stream().map(studentEntity -> {
            StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
            ClassEntity classEntity = classService.getClassByStudentId(studentEntity.getId());
            studentDTO.setStudentClass(classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier());
            return studentDTO;
        }).toList();
    }

    @Override
    public boolean existsByCi(String ci) {
        return studentRepository.existsByCi(ci);
    }

    @Override
    public boolean existsByRude(String rude) {
        return studentRepository.existsByRude(rude);
    }

    @Override
    public List<StudentDTO> findAllByParentId(Integer fatherId) {
        List<StudentEntity> studentEntities = studentRepository.findAllByParentId(fatherId);
        return studentEntities.stream().map(studentEntity -> {
            StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
            ClassEntity classEntity = classService.getClassByStudentId(studentEntity.getId());
            studentDTO.setStudentClass(classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier());
            return studentDTO;
        }).toList();
    }

    @Override
    public List<StudentDTO> findAllByClassId(Integer classId) {
        List<StudentEntity> studentEntities = studentRepository.findAllByClassId(classId);
        ClassEntity classEntity = classService.getClass(classId);
        String className = classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier();
        return studentEntities.stream().map(studentEntity -> {
            StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
            studentDTO.setStudentClass(className);
            return studentDTO;
        }).toList();
    }

    @Override
    public List<StudentDTO> findAllByAssignationId(Integer assignationId) {
        List<StudentEntity> studentEntities = studentRepository.findAllByAssignationId(assignationId);
        ClassEntity classEntity = classService.getClassByAssignationId(assignationId);
        String className = classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier();
        return studentEntities.stream().map(studentEntity -> {
            StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
            studentDTO.setStudentClass(className);
            return studentDTO;
        }).toList();
    }
}
