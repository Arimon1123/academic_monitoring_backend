package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Integer> {
    List<ActivityEntity> findAllByAssignationIdAndStatusOrderById(Integer assignationId,Integer status);
}
