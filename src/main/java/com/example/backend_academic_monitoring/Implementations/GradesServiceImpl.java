package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.Repository.ActivityGradeRepository;
import com.example.backend_academic_monitoring.Service.GradesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GradesServiceImpl implements GradesService {
    private final ActivityGradeRepository activityGradeRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(GradesServiceImpl.class);

    public GradesServiceImpl( ActivityGradeRepository activityGradeRepository) {
        this.activityGradeRepository = activityGradeRepository;

    }

    @Override
    public Map<Integer,List<GradesDTO>> getAllGradesByStudentAndYear(Integer studentId, Integer year) {
        List<GradesDTO> grades = activityGradeRepository.findAllByStudentIdAndYear(studentId, year);
        Map<Integer , List<GradesDTO>> gradesMap = new HashMap<>();
        for(GradesDTO grade : grades){
            if(gradesMap.containsKey(grade.getSubject_Id())){
                gradesMap.get(grade.getSubject_Id()).add(grade);
            }else{
                List<GradesDTO> gradesList = new ArrayList<>();
                gradesList.add(grade);
                gradesMap.put(grade.getSubject_Id(),gradesList);
            }
        }
        return gradesMap;
    }
}
