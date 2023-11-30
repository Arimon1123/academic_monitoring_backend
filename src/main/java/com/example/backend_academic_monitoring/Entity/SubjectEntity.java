package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Subselect;

@Entity
@Table(name = "subject")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer hours;
    Integer status;

}
