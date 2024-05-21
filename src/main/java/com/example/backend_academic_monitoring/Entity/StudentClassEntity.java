package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student_class")
@Data
public class StudentClassEntity {
    @Id
    private Integer id;
    @Column(name = "student_id")
    private Integer studentId;
    @OneToOne()
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
    private Integer approvalStatus;
}
