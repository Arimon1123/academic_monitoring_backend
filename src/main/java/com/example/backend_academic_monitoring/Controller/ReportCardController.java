package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Service.ReportCardService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/reportCard")
public class ReportCardController {
    private final ReportCardService reportCardService;

    public ReportCardController(ReportCardService reportCardService) {
        this.reportCardService = reportCardService;
    }

    @GetMapping(value = "", produces = "application/zip")
    public void generateReportCards(@RequestParam List<Integer> classId,
                                    @RequestParam Integer bimester,
                                    @RequestParam boolean isFinalReport,
                                    HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=reportCard.zip");
            response.setHeader("Content-Type", "application/zip");
            File file = new File(reportCardService.generateReportClassList(classId, bimester, isFinalReport));
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(file);
            IOUtils.copy(in, out);
            out.close();
            in.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDTO<String>> testPdfGeneration() throws IOException {
        reportCardService.generateTestReportCard();
        return ResponseEntity.ok(new ResponseDTO<>("Test", "Obtained PDF file successfully!", 200));
    }
}
