package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer year;
    private Integer shift;
    private String identifier;
    private Integer status;
}
