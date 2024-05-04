package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Table(name = "consultation_hours")
@Data
public class ConsultHourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time startTime;
    private Time endTime;
    private String weekday;
    private Integer period;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacherEntity;
}
