package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    StudentEntity findByIdAndStatus(Integer id, Integer status);
    List<StudentEntity> findAllByStatus(Integer status);
    boolean existsByCi(String ci);
    boolean existsByRude(String rude);
}
