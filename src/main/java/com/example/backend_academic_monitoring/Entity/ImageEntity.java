package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String type;
    private String name;

}
