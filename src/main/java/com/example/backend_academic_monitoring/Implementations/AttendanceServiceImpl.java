package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.AttendanceDTO;
import com.example.backend_academic_monitoring.Entity.AttendanceEntity;
import com.example.backend_academic_monitoring.Repository.AttendanceRepository;
import com.example.backend_academic_monitoring.Service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(AttendanceServiceImpl.class);

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

    @Override
    public List<AttendanceDTO> getAttendanceDateByAssignationId(Integer assignationId) {
        List<Date> attendanceDate = attendanceRepository.getAllDateByAssignationId(assignationId);
        List<AttendanceDTO> attendanceList = new ArrayList<>();
        int index = 0;
        for(Date date: attendanceDate){
            index ++ ;
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setId(index);
            attendanceDTO.setDate(date);
            attendanceList.add(attendanceDTO);
        }
        return attendanceList;
    }
}
