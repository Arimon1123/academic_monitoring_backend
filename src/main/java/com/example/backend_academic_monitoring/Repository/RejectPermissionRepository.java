package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.RejectedPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RejectPermissionRepository extends JpaRepository<RejectedPermissionEntity, Integer> {
}
