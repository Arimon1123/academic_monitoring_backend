package com.example.backend_academic_monitoring.DTO;

import lombok.*;


public interface GradesDTO {
    Integer getId();
    Integer getStudent_Id();
    Integer gettotal_grade();
    String getName();
    Integer getBimester();
    Integer getSubject_Id();
    String getSubject_Name();
}
