package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.FeatureEntity;
import com.example.backend_academic_monitoring.Entity.RequirementEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDTO {
    private Integer id;
    private String name;
    private Integer hours;
    private Integer status;
    private String gradeName;
    private String section;
    private List<RequirementEntity> requirements;
}
