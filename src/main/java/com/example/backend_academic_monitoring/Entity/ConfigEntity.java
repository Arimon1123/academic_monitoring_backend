package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "configuration")
@Data
public class ConfigEntity {
    @Id
    Integer id;
    Integer currentBimester;
    Integer currentYear;
}
