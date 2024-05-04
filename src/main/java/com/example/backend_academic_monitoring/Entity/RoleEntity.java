package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "role")
@Entity
@Data
@EqualsAndHashCode
public class RoleEntity {
    @Id
    private Integer id;
    private String name;
}
