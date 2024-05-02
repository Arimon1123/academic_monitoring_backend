package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teacher_subject")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Column(name = "subject_id")
    private Integer subjectId;
}
