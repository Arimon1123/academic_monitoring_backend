package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import com.example.backend_academic_monitoring.DTO.ReportCardDTO;
import com.example.backend_academic_monitoring.DTO.StudentDTO;
import com.example.backend_academic_monitoring.Entity.ClassEntity;
import com.example.backend_academic_monitoring.Repository.ClassRepository;
import com.example.backend_academic_monitoring.Service.ClassService;
import com.example.backend_academic_monitoring.Service.GradesService;
import com.example.backend_academic_monitoring.Service.ReportCardService;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReportCardServiceImpl.class);
    private final GradesService gradesService;
    private final ClassService classService;
    private final StudentService studentService;
    private final TemplateEngine templateEngine;
    private final ClassRepository classRepository;

    public ReportCardServiceImpl(GradesService gradesService, ClassService classService, StudentService studentService, TemplateEngine templateEngine, ClassRepository classRepository) {
        this.gradesService = gradesService;
        this.classService = classService;
        this.studentService = studentService;
        this.templateEngine = templateEngine;
        this.classRepository = classRepository;
    }

    @Override
    public String generateReportClassList(List<Integer> classIds, Integer bimester, boolean isFinalReport) throws IOException {
        String outputFolder = System.getProperty("user.dir") + File.separator + "reportCards";
        for (Integer id : classIds) {
            ClassEntity classEntity = classRepository.getReferenceById(id);
            generateReportCardsByClass(classEntity, bimester, isFinalReport, outputFolder);
        }
        createZipFile(outputFolder, outputFolder);
        File file = new File(outputFolder);
        LOGGER.info("Deleting directory {}", outputFolder);
        try {
            FileUtils.deleteDirectory(file);
            LOGGER.info("Directory deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFolder + ".zip";
    }

    @Override
    public void generateTestReportCard() throws IOException {
        StudentDTO studentDTO = studentService.getStudent(5);
        ClassEntity classEntity = classService.getClass(1);
        String outputFolder = System.getProperty("user.dir") + File.separator + "reportCards";
        Map<Integer, List<GradesDTO>> gradeMap = gradesService.getAllGradesByStudentAndYear(studentDTO.getId(), 2024);
        List<ReportCardDTO> reportCards = new ArrayList<>();
        for (Integer gradeId : gradeMap.keySet()) {
            List<GradesDTO> grades = gradeMap.get(gradeId);
            ReportCardDTO reportCardDTO = new ReportCardDTO();
            reportCardDTO.setSubjectName(grades.get(0).getSubject_Name());
            float avg = 0;
            for (int i = 0; i < 4; i++) {
                try {
                    float grade = grades.get(i).gettotal_grade();
                    avg += grade;
                    switch (i) {
                        case 0:
                            reportCardDTO.setFirstGrade((int) Math.ceil(grade));
                            break;
                        case 1:
                            reportCardDTO.setSecondGrade((int) Math.ceil(grade));
                            break;
                        case 2:
                            reportCardDTO.setThirdGrade((int) Math.ceil(grade));
                            break;
                        case 3:
                            reportCardDTO.setFourthGrade((int) Math.ceil(grade));
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    switch (i) {
                        case 0:
                            reportCardDTO.setFirstGrade(0);
                            break;
                        case 1:
                            reportCardDTO.setSecondGrade(0);
                            break;
                        case 2:
                            reportCardDTO.setThirdGrade(0);
                            break;
                        case 3:
                            reportCardDTO.setFourthGrade(0);
                            break;
                    }
                }
            }
            LOGGER.info("Nota {}", avg);
            reportCardDTO.setAverage((int) Math.ceil(avg / 4));
            reportCards.add(reportCardDTO);
        }
        String fullName = studentDTO.getFatherLastname() + " " + studentDTO.getMotherLastname() + " " + studentDTO.getName();
        String html = buildReportCard(studentDTO, "1° Primaria A", classEntity, reportCards, 4, true, outputFolder);
        LOGGER.info("html {}", html);
        createPdf(outputFolder, fullName, html);
    }

    @Override
    public void generateReportCardsByClass(ClassEntity classEntity, Integer bimester, boolean isFinalReport, String outputFolder) throws IOException {
        List<StudentDTO> studentList = studentService.findAllByClassId(classEntity.getId());
        String className = classService.getClassName(classEntity.getId());
        String localOutPutFolder = outputFolder + File.separator + className.replace(" ", "_");
        for (StudentDTO studentDTO : studentList) {
            Map<Integer, List<GradesDTO>> gradeMap = gradesService.getAllGradesByStudentAndYear(studentDTO.getId(), classEntity.getYear());
            List<ReportCardDTO> reportCards = new ArrayList<>();
            for (Integer gradeId : gradeMap.keySet()) {
                List<GradesDTO> grades = gradeMap.get(gradeId);
                ReportCardDTO reportCardDTO = new ReportCardDTO();
                reportCardDTO.setSubjectName(grades.get(0).getSubject_Name());
                float avg = 0;
                for (int i = 0; i < 4; i++) {
                    try {
                        float grade = grades.get(i).gettotal_grade();
                        avg += (int) grade;
                        switch (i) {
                            case 0:
                                reportCardDTO.setFirstGrade((int) Math.ceil(grade));
                                break;
                            case 1:
                                reportCardDTO.setSecondGrade((int) Math.ceil(grade));
                                break;
                            case 2:
                                reportCardDTO.setThirdGrade((int) Math.ceil(grade));
                                break;
                            case 3:
                                reportCardDTO.setFourthGrade((int) Math.ceil(grade));
                                break;
                        }
                    } catch (IndexOutOfBoundsException e) {
                        switch (i) {
                            case 0:
                                reportCardDTO.setFirstGrade(0);
                                break;
                            case 1:
                                reportCardDTO.setSecondGrade(0);
                                break;
                            case 2:
                                reportCardDTO.setThirdGrade(0);
                                break;
                            case 3:
                                reportCardDTO.setFourthGrade(0);
                                break;
                        }
                    }
                }
                if (isFinalReport) {
                    reportCardDTO.setAverage((int) Math.ceil(avg / 4));
                }
                reportCards.add(reportCardDTO);
            }
            String fullName = studentDTO.getFatherLastname() + " " + studentDTO.getMotherLastname() + " " + studentDTO.getName();
            String html = buildReportCard(studentDTO, className, classEntity, reportCards, bimester, isFinalReport, localOutPutFolder);
            createPdf(localOutPutFolder, fullName, html);
        }
    }


    public String buildReportCard(StudentDTO studentDTO, String className, ClassEntity classEntity, List<ReportCardDTO> reportCards, Integer bimester, boolean isFinalReport, String outPutFolder) throws IOException {
        String fullName = studentDTO.getFatherLastname() + " " + studentDTO.getMotherLastname() + " " + studentDTO.getName();
        Context context = new Context();
        context.setVariable("rude", studentDTO.getRude());
        context.setVariable("fullname", fullName);
        context.setVariable("className", className);
        context.setVariable("year", classEntity.getYear());
        context.setVariable("subjects", reportCards);
        context.setVariable("bimester", bimester);
        context.setVariable("isFinalReport", isFinalReport);
        return templateEngine.process("ReportCardTemplate", context);
    }

    private void createPdf(String outPutFolder, String fullName, String html) {
        String outPutFileName = outPutFolder + File.separator + fullName.replace(" ", "_") + ".pdf";
        if (!new File(outPutFolder).exists()) {
            LOGGER.info("Creating directory {}", outPutFolder);
            try {
                if (new File(outPutFolder).mkdirs())
                    LOGGER.info("Directory created");
                else
                    throw new RuntimeException("Error trying to create directory");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            OutputStream outputStream = new FileOutputStream(outPutFileName);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void createZipFile(String zipName, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipName + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(fileName);
        zipFile(fileToZip, fileToZip.getName(), zipOut);
        zipOut.close();
        fos.close();
    }

    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
