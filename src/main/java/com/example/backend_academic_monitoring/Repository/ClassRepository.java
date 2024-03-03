package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer>{

}
