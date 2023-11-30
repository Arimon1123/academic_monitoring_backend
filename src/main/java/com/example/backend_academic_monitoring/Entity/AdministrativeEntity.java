package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrative")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdministrativeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String lastname;
    private Integer status;
}
