package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequirementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String requirement;
    private String description;
}
