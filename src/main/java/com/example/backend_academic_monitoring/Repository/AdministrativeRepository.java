package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.AdministrativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrativeRepository extends JpaRepository<AdministrativeEntity, Integer> {
}
