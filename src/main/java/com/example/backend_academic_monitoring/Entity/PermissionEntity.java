package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "permission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private Timestamp permissionStartDate;
    private Timestamp permissionEndDate;
    private String reason;
    private Integer status;
    private Integer studentId;
    private Integer permissionStatus;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "permission_image",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<ImageEntity> images;
}
