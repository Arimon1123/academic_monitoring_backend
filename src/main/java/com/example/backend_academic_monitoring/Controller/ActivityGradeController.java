package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ActivityGradeEntity;
import com.example.backend_academic_monitoring.Service.ActivityGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activityGrade")
public class ActivityGradeController {
    private final ActivityGradeService activityGradeService;
    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityGradeController.class);

    public ActivityGradeController(ActivityGradeService activityGradeService) {
        this.activityGradeService = activityGradeService;
    }
    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> saveGrades(@RequestBody List<ActivityGradeEntity> activityGradeEntityList) {
        try{
            LOGGER.info("Saving grades {}", activityGradeEntityList);
            activityGradeService.saveGrades(activityGradeEntityList);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Grades saved successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error saving grades",500));
        }
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<ResponseDTO<List<ActivityGradeEntity>>> getGradesByActivityId(@PathVariable Integer activityId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByActivityId(activityId), "Grades found successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error finding grades",500));
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<ActivityGradeEntity>>> getGradesByStudentIdAndAssignationId(@RequestParam Integer studentId, @RequestParam Integer assignationId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByStudentIdAndAssignationId(studentId, assignationId), "Grades found successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error finding grades",500));
        }
    }

    @GetMapping("/assignation/{assignationId}")
    public ResponseEntity<ResponseDTO<Map<Integer,List<ActivityGradeEntity>>>> getGradesByAssignationId(@PathVariable Integer assignationId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByAssignationId(assignationId), "Grades found successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error finding grades",500));
        }
    }
}
