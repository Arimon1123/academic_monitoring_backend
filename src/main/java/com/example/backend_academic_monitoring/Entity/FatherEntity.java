package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "father")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String address;
    private String email;
    private String phone;
    private Integer status;
}
