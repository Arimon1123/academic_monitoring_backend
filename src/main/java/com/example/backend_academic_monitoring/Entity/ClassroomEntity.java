package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classroom")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private String block;
    private String type;
}
