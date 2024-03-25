package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "activity_has_grade")
public class ActivityGradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    private Integer grade;
    private Integer activityId;
    private Integer studentId;
}
