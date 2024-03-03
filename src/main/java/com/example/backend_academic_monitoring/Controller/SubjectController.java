package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.SubjectCreateDTO;
import com.example.backend_academic_monitoring.DTO.SubjectDTO;
import com.example.backend_academic_monitoring.Service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> save(@RequestBody SubjectCreateDTO subjectDTO ){
        try{
            subjectService.save(subjectDTO);
            return ResponseEntity.ok(new ResponseDTO<>(null,"Materia guardada correctamente", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping()
    public ResponseEntity<ResponseDTO<List<SubjectDTO>>> getAll(){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(subjectService.getAll(),"Materias obtenidas correctamente", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/{gradeId}")
    public ResponseEntity<ResponseDTO<List<SubjectDTO>>> getByGrade(@PathVariable Integer gradeId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(subjectService.getByGrade(gradeId),"Materias obtenidas correctamente", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<ResponseDTO<List<SubjectDTO>>> getByTeacher(@PathVariable Integer teacherId){
        try{
            return ResponseEntity.ok(new ResponseDTO<>(subjectService.getByTeacher(teacherId),"Materias obtenidas correctamente", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,e.getMessage(), 500));
        }
    }


}
