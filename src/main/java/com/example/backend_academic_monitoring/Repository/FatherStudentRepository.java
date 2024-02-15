package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.FatherStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatherStudentRepository extends JpaRepository<FatherStudentEntity, Integer> {

}
