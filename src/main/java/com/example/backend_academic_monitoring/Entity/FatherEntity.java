package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "father")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @Column(name = "acad_user_id")
    private Integer userId;
}
