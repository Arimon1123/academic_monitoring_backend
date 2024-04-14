package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ActivityGradeId {
    private Integer studentId;
    private Integer activityId;
}
