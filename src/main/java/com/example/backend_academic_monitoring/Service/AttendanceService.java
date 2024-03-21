package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.AttendanceEntity;

import java.util.List;

public interface AttendanceService {
    void createAttendance(List<AttendanceEntity> attendanceEntities);
    List<AttendanceEntity> getAllAttendanceByAssignation(Integer assignationId);
}
