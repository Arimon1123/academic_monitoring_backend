package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.StudentCreateDTO;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
