package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @EmbeddedId
    private ActivityGradeId id;
    private Integer status;
    private Integer grade;
    @Column(insertable = false, updatable = false)
    private Integer activityId;
    @Column(insertable = false, updatable = false)
    private Integer studentId;
}
