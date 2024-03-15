package com.example.backend_academic_monitoring.Entity;

import com.example.backend_academic_monitoring.DTO.DimensionEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "activity")
public class ActivityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer value;
    private Integer bimester;
    @Enumerated(EnumType.STRING)
    private DimensionEnum dimension;
    private Integer status;
}
