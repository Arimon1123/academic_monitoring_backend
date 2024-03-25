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
            return ResponseEntity.ok(new ResponseDTO<>(null, "Activity created successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error creating activity",500));
        }
    }

    @PutMapping()
    public ResponseEntity<ResponseDTO<String>> updateActivity(@RequestBody ActivityEntity activityCreateDTO) {
        try {
            activityService.updateActivity(activityCreateDTO);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Activity updated successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error updating activity",500));
        }
    }
    @GetMapping("/assignation/{assignationId}")
    public ResponseEntity<ResponseDTO<List<ActivityEntity>>> findActivitiesByAssignationId(@PathVariable Integer assignationId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(activityService.findActivitiesByAssignationId(assignationId), "Activities found successfully",200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, "Error finding activities",500));
        }
    }
}
