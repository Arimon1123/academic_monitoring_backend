package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ClassListDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Entity.StudentEntity;
import com.example.backend_academic_monitoring.Repository.ClassRepository;
import com.example.backend_academic_monitoring.Repository.ClassAssignationRepository;
import com.example.backend_academic_monitoring.Service.ClassService;
import com.example.backend_academic_monitoring.Service.ClassroomService;
import com.example.backend_academic_monitoring.Service.SubjectService;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    @Override
    public ClassEntity getClass(Integer classId) {
        return classRepository.getReferenceById(classId);
    }

    @Override
    public void addStudentToClass(Integer classId, StudentEntity student) {
        ClassEntity classEntity = classRepository.getReferenceById(classId);
        classEntity.getStudents().add(student);
        classRepository.save(classEntity);
    }

    @Override
    public List<ClassListDTO> getClassByGradeAndYearAndShift(Integer gradeId, Integer year, Integer shift) {
        List<ClassEntity> classEntities = classRepository.findByGrade_IdAndYearAndShift(gradeId, year, shift);
        return classEntities.stream().map(
                classEntity ->{
                    ClassListDTO classListDTO = new ClassListDTO();
                    classListDTO.setId(classEntity.getId());
                    classListDTO.setYear(classEntity.getYear());
                    classListDTO.setShift(classEntity.getShift());
                    classListDTO.setIdentifier(classEntity.getIdentifier());
                    classListDTO.setGrade(classEntity.getGrade().getNumber()+ "°" + classEntity.getGrade().getSection());
                    classListDTO.setStudentCount(classRepository.getStudentCount(classEntity.getId()));
                    return classListDTO;
                }
        ).toList();
    }

    @Override
    public String getClassName(Integer classId) {
        ClassEntity classEntity = classRepository.getReferenceById(classId);
        return classEntity.getGrade().getNumber() + "°" + classEntity.getGrade().getSection() + " " + classEntity.getIdentifier();
    }

    @Override
    public ClassEntity getClassByStudentId(Integer studentId) {
        return classRepository.findByStudentsId(studentId);
    }

    @Override
    public ClassEntity getClassByAssignationId(Integer assignationId) {
        return classRepository.findByAssignation(assignationId);
    }


}
