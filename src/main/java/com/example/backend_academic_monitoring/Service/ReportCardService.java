package com.example.backend_academic_monitoring.Service;

import java.io.FileNotFoundException;

public interface ReportCardService {
    void generateReportCard(Integer classId) throws FileNotFoundException;
}
