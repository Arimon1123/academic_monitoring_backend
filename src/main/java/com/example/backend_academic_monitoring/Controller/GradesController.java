package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import com.example.backend_academic_monitoring.Service.GradesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/grades")
@RestController
public class GradesController {
    private final GradesService gradesService;
    private final ActivityGradeService activityGradeService;

    public GradesController(GradesService gradesService, ActivityGradeService activityGradeService) {
        this.gradesService = gradesService;
        this.activityGradeService = activityGradeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<Map<Integer,List<GradesDTO>>>> getAllGradesByStudentAndYear(@RequestParam Integer studentId, @RequestParam Integer year) {
        try{
            return ResponseEntity.ok(new ResponseDTO<>(gradesService.getAllGradesByStudentAndYear(studentId, year), "Grades retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null,e.getMessage(),500));
        }
    }
    @GetMapping("/activities")
    public ResponseEntity<ResponseDTO<List<ActivityGradeEntity>>> getAllActivityGradesByStudent(@RequestParam Integer assignationId, @RequestParam Integer studentId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByStudentIdAndAssignationId(assignationId,studentId), "Grades retrieved succesfully",200));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(),500));
        }
    }
}