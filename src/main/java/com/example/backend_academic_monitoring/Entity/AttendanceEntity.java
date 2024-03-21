package com.example.backend_academic_monitoring.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer attendance;
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "es_BO", timezone = "America/La_Paz")
    private Date date;
    @Column(name = "class_has_subject_id")
    private Integer assignationId;
    private Integer studentId;
}
