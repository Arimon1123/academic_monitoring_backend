package com.example.backend_academic_monitoring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "subject")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer hours;
    Integer status;
    @OneToOne
    @JoinColumn(name = "grade_id")
    GradeEntity grade;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subject_requirement",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "requirements_id")
    )
    List<RequirementEntity> requirements;

}
