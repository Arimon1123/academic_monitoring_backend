package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.AttendanceEntity;
import com.example.backend_academic_monitoring.Repository.AttendanceRepository;
import com.example.backend_academic_monitoring.Service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void createAttendance(List<AttendanceEntity> attendanceEntities) {
        attendanceRepository.saveAll(attendanceEntities);
    }

    @Override
    public List<AttendanceEntity> getAllAttendanceByAssignation(Integer assignationId) {
        return attendanceRepository.getAllByAssignationIdOrderByDateDesc(assignationId);
    }
}
