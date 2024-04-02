package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AttendanceDTO;
import com.example.backend_academic_monitoring.Entity.AttendanceEntity;

import java.sql.Date;
import java.util.List;

public interface AttendanceService {
    void createAttendance(List<AttendanceEntity> attendanceEntities);
    List<AttendanceEntity> getAllAttendanceByAssignation(Integer assignationId);
    List<AttendanceDTO> getAttendanceDateByAssignationId(Integer assignationId);
}
