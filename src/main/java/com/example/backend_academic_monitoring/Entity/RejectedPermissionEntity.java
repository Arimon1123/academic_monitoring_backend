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
    @Column(name = "id")
    private Integer id;
    private String reason;
    @Column(name = "permission_id")
    private Integer permissionId;
}
