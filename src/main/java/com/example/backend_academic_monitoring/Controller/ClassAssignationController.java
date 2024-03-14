package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.AssignationCreateDTO;
import com.example.backend_academic_monitoring.DTO.ClassAssignationDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ClassAssignationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/classAssignation")
@RestController
public class ClassAssignationController {
    private final ClassAssignationService classAssignationService;

    public ClassAssignationController(ClassAssignationService classAssignationService) {
        this.classAssignationService = classAssignationService;
    }

    @GetMapping("/schedule/{classroomId}")
    public ResponseEntity<ResponseDTO<List<ClassAssignationDTO>>> findClassroomSchedule(@PathVariable(value = "classroomId") Integer classroomId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(classAssignationService.getClassAssignationByClassroomId(classroomId),"Classrooms retrieved successfully",200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO<String>> createClassAssignation(@RequestBody AssignationCreateDTO classAssignationDTO){
        try{
            classAssignationService.createClassAssignation(classAssignationDTO);
            return ResponseEntity.ok(new ResponseDTO<>("Class assignation created successfully", "Class assignation created successfully", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ResponseDTO<List<ClassAssignationDTO>>> findTeacherSchedule(@PathVariable(value = "teacherId") Integer teacherId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(classAssignationService.getClassAssignationByTeacherId(teacherId),"Teacher schedule retrieved successfully",200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseDTO<List<ClassAssignationDTO>>> findClassSchedule(@PathVariable(value = "classId") Integer classId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(classAssignationService.getClassAssignationByClassId(classId),"Class schedule retrieved successfully",200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}
