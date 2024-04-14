package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.Entity.ClassEntity;

import java.io.IOException;
import java.util.List;

public interface ReportCardService {
    void generateReportCardsByClass(ClassEntity classEntity, Integer bimester, boolean isFinalReport, String outputFolder) throws IOException;

    String generateReportClassList(List<Integer> classIds, Integer bimester, boolean isFinalReport) throws IOException;
}
