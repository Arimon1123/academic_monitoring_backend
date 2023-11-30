package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Entity
@Table(name = "feature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String feature;
}
