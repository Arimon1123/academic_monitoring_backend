package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    public static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> save(@RequestBody StudentCreateDTO studentDTO ){
        LOGGER.info("Guardando  {}", studentDTO);
        studentService.saveStudent(studentDTO);
        return  ResponseEntity.ok(new ResponseDTO<>(null,"Estudiante guardado correctamente", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("exists/ci/{ci}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByCi(@PathVariable String ci){
        try {
           return ResponseEntity.ok(new ResponseDTO<>(studentService.existsByCi(ci),"Busqueda exitosa", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,"Error al buscar", 500));
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("exists/rude/{rude}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByRude(@PathVariable String rude){
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.existsByRude(rude),"Busqueda exitosa", 200));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDTO<>(null,"Error al buscar", 500));
        }
    }

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> findAllByFatherId(@PathVariable Integer parentId){
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.findAllByParentId(parentId),"Estudiantes encontrados", 200));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null,"Error al buscar", 500));
        }
    }
}
