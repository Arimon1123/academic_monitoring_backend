package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    private Integer status;
}
