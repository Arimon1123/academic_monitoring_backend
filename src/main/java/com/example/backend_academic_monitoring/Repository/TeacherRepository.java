package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer>{

    TeacherEntity findByUserId(Integer userId);
}
