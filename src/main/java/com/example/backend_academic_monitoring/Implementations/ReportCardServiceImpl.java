package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.DTO.ReportCardDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    private final GradesService gradesService;
    private final ClassService classService;
    private final StudentService studentService;
    private final TemplateEngine templateEngine;
    public static final Logger LOGGER = LoggerFactory.getLogger(ReportCardServiceImpl.class);
    public ReportCardServiceImpl(GradesService gradesService, ClassService classService, StudentService studentService, TemplateEngine templateEngine) {
        this.gradesService = gradesService;
        this.classService = classService;
        this.studentService = studentService;
        this.templateEngine = templateEngine;
    }

    @Override
    public void generateReportCard(Integer classId) throws FileNotFoundException {
        ClassEntity classEntity = classService.getClass(classId);
        List<StudentDTO> studentList = studentService.findAllByClassId(classId);
        String className = classService.getClassName(classId);
        for(StudentDTO studentDTO : studentList) {
            Map<Integer,List<GradesDTO>> gradeMap = gradesService.getAllGradesByStudentAndYear(studentDTO.getId(),classEntity.getYear());
            List<ReportCardDTO> reportCards = new ArrayList<>();
            for(Integer gradeId : gradeMap.keySet()) {
                List<GradesDTO> grades = gradeMap.get(gradeId);
                ReportCardDTO reportCardDTO = new ReportCardDTO();
                Integer firstGrade = 0,secondGrade = 0,thirdGrade = 0,fourthGrade = 0;
                reportCardDTO.setSubjectName(grades.get(0).getSubject_Name());
                try{
                    firstGrade = grades.get(0).gettotal_grade();
                    reportCardDTO.setFirstGrade(firstGrade);
                }catch (IndexOutOfBoundsException e){
                    reportCardDTO.setFirstGrade(0);
                }
                try{

                    secondGrade = grades.get(1).gettotal_grade();
                    reportCardDTO.setSecondGrade(secondGrade);
                }catch (IndexOutOfBoundsException e){
                    reportCardDTO.setSecondGrade(0);
                }
                try{
                    thirdGrade = grades.get(2).gettotal_grade();
                    reportCardDTO.setThirdGrade(thirdGrade);
                }catch (IndexOutOfBoundsException e){
                    reportCardDTO.setThirdGrade(0);
                }
                try{
                    fourthGrade = grades.get(3).gettotal_grade();
                    reportCardDTO.setFourthGrade(fourthGrade);
                }catch (IndexOutOfBoundsException e){
                    reportCardDTO.setFourthGrade(0);
                }
                Integer avg = Math.round ((float) (firstGrade + secondGrade + thirdGrade + fourthGrade) /4);
                reportCardDTO.setAverage(avg);
                reportCards.add(reportCardDTO);
            }
            buildReportCard(studentDTO,className,classEntity,reportCards);
        }
    }

    public void buildReportCard(StudentDTO studentDTO,String className,ClassEntity classEntity, List<ReportCardDTO> reportCards) throws FileNotFoundException {
        String fullName = studentDTO.getFatherLastname()+" "+studentDTO.getMotherLastname()+" "+studentDTO.getName();
        Context context = new Context();
        context.setVariable("rude", studentDTO.getRude());
        context.setVariable("fullname", fullName);
        context.setVariable("className",className);
        context.setVariable("year",classEntity.getYear());
        context.setVariable("subjects",reportCards);
        String html = templateEngine.process("ReportCardTemplate",context);
        String outPutFolder = System.getProperty("user.dir") + File.separator +className.replace(" ","_");
        String outPutFileName = outPutFolder +  File.separator + fullName.replace(" ","_")+".pdf";
        if (!new File(outPutFolder).exists()) {
            LOGGER.info("Creating directory {}", outPutFolder);
            try{
                if (new File(outPutFolder).mkdir())
                    LOGGER.info("Directory created");
                else
                    throw new RuntimeException("Error trying to create directory");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try{
            OutputStream outputStream = new FileOutputStream(outPutFileName);
            ITextRenderer renderer = new ITextRenderer();
            LOGGER.info("HTML {}",html);
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
