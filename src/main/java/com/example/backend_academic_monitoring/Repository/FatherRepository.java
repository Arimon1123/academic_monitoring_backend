package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.FatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherRepository extends JpaRepository<FatherEntity, Integer>{
}
