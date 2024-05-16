package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<ConfigEntity, Integer> {
}
