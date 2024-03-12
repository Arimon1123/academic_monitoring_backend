package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ClassSubjectDTO;
import com.example.backend_academic_monitoring.DTO.ClassroomDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ClassroomService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ResponseDTO<List<ClassroomDTO>>> findClassroomByRequirement(@RequestParam(value = "requirements") Integer [] requirements){
        List <Integer> requirementsList = List.of(requirements);
        return ResponseEntity.ok(new ResponseDTO<>(classroomService.getClassroomsByRequirement(requirementsList),"Classrooms retrieved successfully",200));
    }

    @GetMapping("/schedule/{classroomId}")
    public ResponseEntity<ResponseDTO<List<ClassSubjectDTO>>> findClassroomSchedule(@PathVariable(value = "classroomId") Integer classroomId){
        return ResponseEntity.ok(new ResponseDTO<>(classroomService.getClassroomSubjects(classroomId),"Classrooms retrieved successfully",200));
    }
}
