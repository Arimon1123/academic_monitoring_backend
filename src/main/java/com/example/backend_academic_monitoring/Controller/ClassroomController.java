package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.DTO.ClassroomDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ClassroomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<ClassroomDTO>>> findClassroomByRequirement(@RequestParam(value = "requirements", required = false) Integer [] requirements){
        if(requirements == null){
            return ResponseEntity.ok(new ResponseDTO<>(classroomService.getAllClassrooms(List.of()),"Classrooms retrieved successfully",200));
        }
        List<Integer> requirementsList = List.of(requirements);
        return ResponseEntity.ok(new ResponseDTO<>(classroomService.getClassroomsByRequirement(requirementsList),"Classrooms retrieved successfully",200));
    }


}
