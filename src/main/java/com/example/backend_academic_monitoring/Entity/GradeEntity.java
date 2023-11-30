package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grade")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String section;
    private String number;
}
