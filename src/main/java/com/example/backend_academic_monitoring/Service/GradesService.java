package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.GradesDTO;

import java.util.List;
import java.util.Map;

public interface GradesService {
    Map<Integer,List<GradesDTO>> getAllGradesByStudentAndYear(Integer studentId, Integer year);
}
