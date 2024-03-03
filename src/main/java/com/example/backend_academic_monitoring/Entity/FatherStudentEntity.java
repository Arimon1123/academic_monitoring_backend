package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "student_father")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FatherStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "father_id")
    private FatherEntity father;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private StudentEntity student;
}
