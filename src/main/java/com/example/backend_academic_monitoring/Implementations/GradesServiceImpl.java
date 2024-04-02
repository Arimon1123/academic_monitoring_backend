package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.Repository.TotalGradesRepository;
import com.example.backend_academic_monitoring.Service.GradesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradesServiceImpl implements GradesService {
    private final TotalGradesRepository totalGradesRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(GradesServiceImpl.class);

    public GradesServiceImpl(TotalGradesRepository totalGradesRepository) {
        this.totalGradesRepository = totalGradesRepository;
    }

    @Override
    public List<GradesDTO> getAllGradesByStudentAndYear(Integer studentId, Integer year) {
        List<GradesDTO> grades = totalGradesRepository.gradeList(studentId, year,1);
        LOGGER.info("Grades for student {} and year {} retrieved successfully", studentId, year);
        return grades;
    }
}
