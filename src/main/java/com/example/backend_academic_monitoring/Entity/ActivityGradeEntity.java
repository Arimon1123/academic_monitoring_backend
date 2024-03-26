package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activity_has_grade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityGradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    private Integer grade;
    private Integer activityId;
    private Integer studentId;
}
