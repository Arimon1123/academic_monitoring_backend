package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ReportCardService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportCard")
public class ReportCardController {
    private final ReportCardService reportCardService;

    public ReportCardController(ReportCardService reportCardService) {
        this.reportCardService = reportCardService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO<String>>generateReportCards(@RequestParam Integer classId){
        try{
            reportCardService.generateReportCard(classId);
            return ResponseEntity.ok(new ResponseDTO<>(null,"Report cards generated Succesfully",200));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ResponseDTO<>(null,e.getLocalizedMessage(),400));
        }
    }
}
