package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.GradesDTO;

import java.util.List;

public interface GradesService {
    List<GradesDTO> getAllGradesByStudentAndYear(Integer studentId, Integer year);
}
