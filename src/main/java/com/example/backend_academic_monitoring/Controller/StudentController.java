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
    public static Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> save(@RequestBody StudentCreateDTO studentDTO) {
        LOGGER.info("Guardando  {}", studentDTO);
        studentService.saveStudent(studentDTO);
        return ResponseEntity.ok(new ResponseDTO<>(null, "Student saved successfully", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("exists/ci/{ci}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByCi(@PathVariable String ci) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.existsByCi(ci), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("exists/rude/{rude}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByRude(@PathVariable String rude) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.existsByRude(rude), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PreAuthorize("hasRole('ROLE_PARENT')")
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> findAllByFatherId(@PathVariable Integer parentId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.findAllByParentId(parentId), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> findAllByClassId(@PathVariable Integer classId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.findAllByClassId(classId), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/assignation/{assignationId}")
    public ResponseEntity<ResponseDTO<List<StudentDTO>>> findAllByAssignationId(@PathVariable Integer assignationId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.findAllByAssignationId(assignationId), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<StudentDTO>> getStudent(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(studentService.getStudent(id), "\n" +
                    "Successfully searched student", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PutMapping("{id}/parent")
    public ResponseEntity<ResponseDTO<String>> updateParents(@PathVariable Integer id, @RequestBody List<Integer> parentsId) {
        try {
            LOGGER.info("Actualizando padres del estudiante {}", id);
            LOGGER.info("Padres nuevos {}", parentsId);
            studentService.updateStudentParents(id, parentsId);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Parents updated successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @PutMapping("{id}/class/{classId}")
    public ResponseEntity<ResponseDTO<String>> updateClass(@PathVariable Integer id, @PathVariable Integer classId) {
        try {
            LOGGER.info("Actualizando clase del estudiante {}", id);
            LOGGER.info("Clase nueva {}", classId);
            studentService.updateStudentClass(id, classId);
            return ResponseEntity.ok(new ResponseDTO<>(null, "Class updated successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

}
