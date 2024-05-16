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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    public static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final ParentStudentRepository parentStudentRepository;
    private final ParentService parentService;
    private final ClassService classService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ParentStudentRepository parentStudentRepository, ParentService parentService, ClassService classService) {
        this.studentRepository = studentRepository;
        this.parentStudentRepository = parentStudentRepository;
        this.parentService = parentService;
        this.classService = classService;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStudent(StudentCreateDTO studentDTO, UserEntity userEntity) {
        StudentEntity studentEntity = StudentMapper.toEntity(studentDTO);
        studentEntity.setUser(userEntity);
        studentEntity = studentRepository.save(studentEntity);
        for (Integer parentId : studentDTO.getParentId()) {
            ParentEntity parentEntity = parentService.getParent(parentId);
            ParentStudentEntity fatherStudentEntity = new ParentStudentEntity();
            fatherStudentEntity.setParent(parentEntity);
            fatherStudentEntity.setStudent(studentEntity);
            parentStudentRepository.save(fatherStudentEntity);
        }
        if (studentDTO.isUser()) {
            UserEntity user = new UserEntity();
            user.setUsername(studentDTO.getCi());
            user.setStatus(1);
        }
        classService.addStudentToClass(studentDTO.getClassId(), studentEntity);
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = studentRepository.getReferenceById(studentDTO.getId());
        studentEntity.setName(studentDTO.getName());
        studentEntity.setFatherLastname(studentDTO.getFatherLastname());
        studentEntity.setMotherLastname(studentDTO.getMotherLastname());
        studentEntity.setEmail(studentDTO.getEmail());
        studentEntity.setAddress(studentDTO.getAddress());
        studentEntity.setBirthdate(studentDTO.getBirthDate());
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
        StudentEntity studentEntity = studentRepository.findByIdAndStatus(id, 1);
        if (studentEntity == null) throw new RuntimeException("Student not found");
        ClassEntity classEntity = classService.getClassByStudentId(id);
        StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
        studentDTO.setStudentClass(classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier());
        return studentDTO;
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentEntity> studentEntities = studentRepository.findAllByStatus(1);
        return studentEntities.stream().map(this::getStudentDTO).toList();
    }

    private StudentDTO getStudentDTO(StudentEntity studentEntity) {
        StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
        ClassEntity classEntity = classService.getClassByStudentId(studentEntity.getId());
        if (classEntity != null) {
            studentDTO.setStudentClass(
                    classEntity.getGrade().getNumber() + "°" +
                            classEntity.getGrade().getSection() + " " +
                            classEntity.getIdentifier());
        }
        return studentDTO;
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
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public List<StudentDTO> findAllByParentId(Integer fatherId) {
        List<StudentEntity> studentEntities = studentRepository.findAllByParentId(fatherId);
        return studentEntities.stream().map(this::getStudentDTO).toList();
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
        try {
            List<StudentEntity> studentEntities = studentRepository.findAllByAssignationId(assignationId);
            ClassEntity classEntity = classService.getClassByAssignationId(assignationId);
            String className = classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier();
            return studentEntities.stream().map(studentEntity -> {
                StudentDTO studentDTO = StudentMapper.toDTO(studentEntity);
                studentDTO.setStudentClass(className);
                return studentDTO;
            }).toList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Page<StudentDTO> searchStudent(String ci, String rude, String name, String lastname, Integer page, Integer size) {
        if (ci == null) ci = "";
        ci = "%" + ci + "%";
        if (rude == null) rude = "";
        rude = "%" + rude + "%";
        if (name == null) name = "";
        name = "%" + name + "%";
        if (lastname == null) lastname = "";
        lastname = "%" + lastname + "%";
        LOGGER.info("Buscando estudiantes por ci: {}, rude: {}, nombre: {}, apellido: {}", ci, rude, name, lastname);
        Page<StudentEntity> studentEntities = studentRepository.searchStudent(ci, rude, name, lastname, PageRequest.of(page, size));
        LOGGER.info("Encontrados {} estudiantes", studentEntities);
        return studentEntities.map(this::getStudentDTO);
    }

    @Override
    public void updateStudentParents(Integer studentId, List<Integer> parentsId) {
        StudentEntity studentEntity = studentRepository.getReferenceById(studentId);
        List<ParentEntity> parentEntityList = new ArrayList<>();
        for (Integer parentId : parentsId) {
            ParentEntity parentEntity = parentService.getParent(parentId);
            parentEntityList.add(parentEntity);
        }
        studentEntity.setParents(parentEntityList);
        LOGGER.info("Guardando {}", studentEntity);
        studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity getStudentEntity(Integer id) {
        return studentRepository.getReferenceById(id);
    }

    @Override
    public void updateStudentClass(Integer studentId, Integer classId) {
        ClassEntity newClassEntity = classService.getClass(classId);
        StudentEntity student = studentRepository.getReferenceById(studentId);
        if (!classService.removeStudentFromClass(newClassEntity, student)) {
            throw new RuntimeException("Error al eliminar estudiante de la clase anterior");
        } else {
            classService.addStudentToClass(classId, student);
        }
    }

    @Override
    public StudentDTO findByUserId(Integer userId) {
        return StudentMapper.toDTO(studentRepository.findByUser_Id(userId));
    }


}
