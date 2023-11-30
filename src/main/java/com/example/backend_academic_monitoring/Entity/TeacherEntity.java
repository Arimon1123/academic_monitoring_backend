package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherEntity {
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
