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
    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityGradeController.class);
    private final ActivityGradeService activityGradeService;

    public ActivityGradeController(ActivityGradeService activityGradeService) {
        this.activityGradeService = activityGradeService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> saveGrades(@RequestBody List<ActivityGradeEntity> activityGradeEntityList) {
        try {
            LOGGER.info("Guardando notas {}", activityGradeEntityList);
            activityGradeService.saveGrades(activityGradeEntityList);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Notas guardadas exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al guardar las notas: " + e.getMessage(), 500));
        }
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<ResponseDTO<List<ActivityGradeEntity>>> getGradesByActivityId(@PathVariable Integer activityId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByActivityId(activityId), "Notas encontradas exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al encontrar las notas: " + e.getMessage(), 500));
        }
    }

    @GetMapping("/assignation/{assignationId}/bimester/{bimester}")
    public ResponseEntity<ResponseDTO<Map<Integer, List<ActivityGradeEntity>>>> getGradesByAssignationId(@PathVariable Integer assignationId, @PathVariable Integer bimester) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityGradeService.getGradesByAssignationIdAndBimester(assignationId, bimester), "Notas encontradas exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al encontrar las notas: " + e.getMessage(), 500));
        }
    }
}