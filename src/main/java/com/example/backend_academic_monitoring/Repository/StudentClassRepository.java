package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.StudentClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentClassRepository extends JpaRepository<StudentClassEntity, Integer> {
    StudentClassEntity findByStudentIdAndClassEntity_Id(Integer studentId, Integer classId);
}
