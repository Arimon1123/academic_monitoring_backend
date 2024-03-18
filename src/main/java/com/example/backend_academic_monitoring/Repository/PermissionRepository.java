package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    List<PermissionEntity> findAllByPermissionStatusAndDateAfter(Integer permissionStatus, Date date);
}
