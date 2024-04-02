package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.AttendanceDTO;
import com.example.backend_academic_monitoring.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Integer > {
    List<AttendanceEntity> getAllByAssignationIdOrderByDateDesc(Integer assignationId);
    @Query("select a.date from AttendanceEntity a where a.assignationId = :assignationId group by a.date order by a.date desc")
    List<Date> getAllDateByAssignationId(Integer assignationId);
}
