package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import com.example.backend_academic_monitoring.Repository.ScheduleRepository;
import com.example.backend_academic_monitoring.Service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleEntity> findAllByClassId(Integer classId) {
        return scheduleRepository.findAllByClassAssignation_ClassId(classId);
    }

    @Override
    public List<ScheduleEntity> findAllByTeacherId(Integer teacherId, Integer year) {
        return scheduleRepository.findAllByClassAssignation_TeacherId(teacherId, year);
    }

    @Override
    public List<ScheduleEntity> findAllByClassroomId(Integer classroomId, Integer year) {
        return scheduleRepository.findAllByClassAssignation_ClassroomId(classroomId, year);
    }

    @Override
    public List<ScheduleEntity> findAllByClassIdAndSubjectId(Integer classId, Integer subjectId) {
        LOGGER.info("Finding schedule by classId {} and subjectId {}", classId, subjectId);
        return scheduleRepository.findAllByClassAssignation_SubjectIdAndClassAssignation_ClassId(subjectId, classId);
    }


}
