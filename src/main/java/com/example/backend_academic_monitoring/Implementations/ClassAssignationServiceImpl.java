package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.Entity.ClassAssignationEntity;
import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import com.example.backend_academic_monitoring.Mappers.ClassAssignationMapper;
import com.example.backend_academic_monitoring.Repository.ClassAssignationRepository;
import com.example.backend_academic_monitoring.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClassAssignationServiceImpl implements ClassAssignationService {
    private final ClassAssignationRepository classAssignationRepository;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;
    private final ClassService classService;
    public static final Logger LOGGER = LoggerFactory.getLogger(ClassAssignationServiceImpl.class);

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
    @Transactional(propagation = Propagation.REQUIRED)
    public void createClassAssignation(AssignationCreateDTO classAssignationDTO) {
        boolean exists = classAssignationRepository.existsByClassIdAndSubjectId(classAssignationDTO.getClassId(), classAssignationDTO.getSubjectId());

        if(exists){
            LOGGER.info("Updating schedule for class assignation");
            updateAssignation(classAssignationDTO);
        }else{
            LOGGER.info("Creating class assignation");
            ClassAssignationEntity classAssignationEntity = ClassAssignationMapper.toEntity(classAssignationDTO);
            classAssignationEntity.setSchedule(mapSchedule(classAssignationDTO, classAssignationEntity));
            classAssignationRepository.save(classAssignationEntity);
        }

    }

    @Override
    public ClassAssignationEntity getClassAssignationByClassIdAndSubjectId(Integer classId, Integer subjectId) {
        return classAssignationRepository.findByClassIdAndSubjectId(classId, subjectId);
    }

    @Override
    public List<ClassAssignationDTO> getClassAssignationByStudentAndYear(Integer studentId, Integer year) {
        List<ClassAssignationEntity> classAssignation = classAssignationRepository.findAllByStudentAndYear(studentId, year);
        LOGGER.info("entities {}" , classAssignation);
        return getClassAssignationDTOS(classAssignation);
    }

    private void updateAssignation(AssignationCreateDTO classAssignationDTO){
        ClassAssignationEntity classAssignationEntity = classAssignationRepository.findByClassIdAndSubjectId(classAssignationDTO.getClassId(), classAssignationDTO.getSubjectId());
        classAssignationEntity.setTeacherId(classAssignationDTO.getTeacherId());
        classAssignationEntity.setClassroomId(classAssignationDTO.getClassroomId());
        classAssignationEntity.getSchedule().clear();
        classAssignationEntity.getSchedule().add(new ScheduleEntity(
                classAssignationDTO.getSchedule().get(0).getId(),
                classAssignationDTO.getSchedule().get(0).getWeekday(),
                classAssignationDTO.getSchedule().get(0).getStartTime(),
                classAssignationDTO.getSchedule().get(0).getEndTime(),
                classAssignationDTO.getSchedule().get(0).getPeriod(),
                classAssignationEntity
        ));
        classAssignationEntity.getSchedule().addAll(mapSchedule(classAssignationDTO, classAssignationEntity));
        LOGGER.info("Updating schedule for class assignation {}", classAssignationEntity.getSchedule());
        classAssignationEntity =  classAssignationRepository.save(classAssignationEntity);
        LOGGER.info("Updated schedule for class assignation {}", classAssignationEntity.getSchedule());

    }

    private List<ScheduleEntity> mapSchedule(AssignationCreateDTO classAssignationDTO, ClassAssignationEntity classAssignationEntity) {
        return classAssignationDTO.getSchedule().stream().map(
                scheduleDTO -> {
                    ScheduleEntity scheduleEntity = new ScheduleEntity();
                    scheduleEntity.setWeekday(scheduleDTO.getWeekday());
                    scheduleEntity.setStartTime(scheduleDTO.getStartTime());
                    scheduleEntity.setEndTime(scheduleDTO.getEndTime());
                    scheduleEntity.setPeriod(scheduleDTO.getPeriod());
                    scheduleEntity.setClassAssignation(classAssignationEntity);
                    return scheduleEntity;
                }
        ).toList();
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
                    classAssignationDTO.setSchedule(classAssignationEntity.getSchedule());
                    return classAssignationDTO;
                }
        ).toList();
    }
}
