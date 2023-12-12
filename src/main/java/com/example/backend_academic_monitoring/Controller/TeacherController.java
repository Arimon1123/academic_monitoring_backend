package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.Entity.SubjectEntity;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeacherController {
    private final TeacherService teacherService;
    public static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

//    @PostMapping("/save")
//    public void saveTeacher(@RequestBody TeacherDTO teacherDTO) {
//
//        LOGGER.info("DTO {}",teacherDTO);
//        teacherService.saveTeacher(teacherDTO, 1);
//    }
}
