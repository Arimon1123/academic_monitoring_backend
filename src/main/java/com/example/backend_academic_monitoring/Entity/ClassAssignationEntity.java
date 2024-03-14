package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//TODO: change table name
@Table(name = "class_has_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassAssignationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer subjectId;
    private Integer classId;
    private Integer teacherId;
    private Integer classroomId;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn(name = "class_has_subject_id", referencedColumnName = "id")
    private List<ScheduleEntity> schedules;
}
