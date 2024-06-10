package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.PermissionCreateDTO;
import com.example.backend_academic_monitoring.DTO.PermissionDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Entity.RejectedPermissionEntity;
import com.example.backend_academic_monitoring.Service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/permission")
public class PermissionController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<String>> savePermission(@RequestParam(value = "permission") String permissionDTO,
                                                              @RequestParam(value = "images") MultipartFile[] image) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PermissionCreateDTO permission = objectMapper.readValue(permissionDTO, PermissionCreateDTO.class);
            LOGGER.info("Saving permission {}", permission);
            permissionService.savePermission(permission, image);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Permission saved successfully", null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<PermissionDTO>>> getAllPermissions(@RequestParam(value = "status", required = false) Integer status) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(permissionService.getAllPermissions(), "Permissions retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<ResponseDTO<List<PermissionDTO>>> getPermissionStatus(@RequestParam Integer statusId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(permissionService.getPermissionStatus(statusId), "Permissions retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PutMapping("/approve/{permissionId}")
    public ResponseEntity<ResponseDTO<String>> approvePermission(@PathVariable Integer permissionId) {
        try {
            permissionService.approvePermission(permissionId);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Permission approved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PutMapping("/reject")
    public ResponseEntity<ResponseDTO<String>> rejectPermission(@RequestBody RejectedPermissionEntity rejectedPermissionEntity) {
        try {
            permissionService.rejectPermission(rejectedPermissionEntity);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Permission rejected successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<ResponseDTO<PermissionDTO>> getPermission(@PathVariable Integer permissionId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(permissionService.getPermission(permissionId), "Permission retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseDTO<List<PermissionDTO>>> getPermissionByClass(@PathVariable Integer classId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(permissionService.getPermissionsByClass(classId), "Permissions retrieved successfully", 200));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseDTO<List<PermissionDTO>>> getPermissionByStudents(@RequestParam List<Integer> studentId, @RequestParam Integer year) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(permissionService.getPermissionByStudents(studentId, year), "Permissions retrieved successfully", 200));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

}
