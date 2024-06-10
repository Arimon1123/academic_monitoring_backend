package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.ActivityEntity;
import com.example.backend_academic_monitoring.Service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> saveActivity(@RequestBody ActivityEntity activityCreateDTO) {
        try {
            activityService.saveActivity(activityCreateDTO);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Actividad creada exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al crear la actividad: " + e.getMessage(), 500));
        }
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO<String>> updateActivity(@RequestBody ActivityEntity activityCreateDTO) {
        try {
            activityService.updateActivity(activityCreateDTO);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Actividad actualizada exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al actualizar la actividad: " + e.getMessage(), 500));
        }
    }

    @GetMapping("/assignation/{assignationId}/bimester/{bimester}")
    public ResponseEntity<ResponseDTO<List<ActivityEntity>>> findActivitiesByAssignationId(@PathVariable Integer assignationId, @PathVariable Integer bimester) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityService.findActivitiesByAssignationIdAndBimester(assignationId, bimester), "Actividades encontradas", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al buscar actividades" + e.getMessage(), 500));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<String>> deleteActivity(@PathVariable Integer id) {
        try {
            activityService.deleteActivity(id);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Actividad eliminada exitosamente", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error al eliminar la actividad" + e.getMessage(), 500));
        }
    }
}