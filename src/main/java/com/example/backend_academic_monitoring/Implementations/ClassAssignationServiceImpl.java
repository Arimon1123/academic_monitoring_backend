package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;
import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import com.example.backend_academic_monitoring.Repository.ClassAssignationRepository;
import com.example.backend_academic_monitoring.Service.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassAssignationServiceImpl implements ClassAssignationService {
    private final ClassAssignationRepository classAssignationRepository;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;
    private final ClassService classService;

    public ClassAssignationServiceImpl(ClassAssignationRepository classAssignationRepository, SubjectService subjectService, TeacherService teacherService, ClassroomService classroomService, ClassService classService) {
        this.classAssignationRepository = classAssignationRepository;
        this.subjectService = subjectService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
        this.classService = classService;
    }

    @Override
    public List<ClassAssignationDTO> getClassAssignationByClassId(Integer classId) {
        List<ClassAssignationEntity> classSubjectEntities = classAssignationRepository.findAllByClassId(classId);
        return getClassAssignationDTOS(classSubjectEntities);
    }

    @Override
    public List<ClassAssignationDTO> getClassAssignationByClassroomId(Integer classroomId) {
        List<ClassAssignationEntity> classSubjectEntities = classAssignationRepository.findAllByClassroomId(classroomId);
        return getClassAssignationDTOS(classSubjectEntities);
    }

    @Override
    public List<ClassAssignationDTO> getClassAssignationByTeacherId(Integer teacherId) {
        List<ClassAssignationEntity> classSubjectEntities = classAssignationRepository.findAllByTeacherId(teacherId);
        return getClassAssignationDTOS(classSubjectEntities);
    }

    @Override
    public void createClassAssignation(AssignationCreateDTO classAssignationDTO) {
        ClassAssignationEntity classAssignationEntity = new ClassAssignationEntity();
        classAssignationEntity.setClassId(classAssignationDTO.getClassId());
        classAssignationEntity.setSubjectId(classAssignationDTO.getSubjectId());
        classAssignationEntity.setTeacherId(classAssignationDTO.getTeacherId());
        classAssignationEntity.setClassroomId(classAssignationDTO.getClassroomId());
        classAssignationEntity.setSchedules(classAssignationDTO.getSchedule());
        for(ScheduleEntity schedule : classAssignationDTO.getSchedule()){
            schedule.setClassAssignation(classAssignationEntity);
        }
        classAssignationRepository.save(classAssignationEntity);
    }

    private List<ClassAssignationDTO> getClassAssignationDTOS(List<ClassAssignationEntity> classSubjectEntities) {
        return classSubjectEntities.stream().map(
                classAssignationEntity -> {
                    ClassAssignationDTO classAssignationDTO = new ClassAssignationDTO();
                    classAssignationDTO.setId(classAssignationEntity.getId());
                    classAssignationDTO.setClassName(classService.getClassName(classAssignationEntity.getClassId()));
                    classAssignationDTO.setSubjectName(subjectService.getSubjectName(classAssignationEntity.getSubjectId()));
                    classAssignationDTO.setTeacherName(teacherService.getTeacherName(classAssignationEntity.getTeacherId()));
                    classAssignationDTO.setClassroomName(classroomService.getClassroomName(classAssignationEntity.getClassroomId()));
                    classAssignationDTO.setSchedule(classAssignationEntity.getSchedules());
                    return classAssignationDTO;
                }
        ).toList();
    }
}
