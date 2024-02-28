package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "role")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleEntity {
    @Id
    private Integer id;
    private String name;
}
