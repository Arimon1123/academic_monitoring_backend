package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Integer > {
    List<AttendanceEntity> getAllByAssignationIdOrderByDateDesc(Integer assignationId);
}
