package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RejectedPermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reason;
}
