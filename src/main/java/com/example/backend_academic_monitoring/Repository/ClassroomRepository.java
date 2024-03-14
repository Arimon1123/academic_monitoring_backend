package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Integer>{
    @Query(value = """
            SELECT c.id, c.number, c.block, c.type\s
            FROM classroom c
            JOIN classroom_requirement cs ON c.id = cs.classroom_id
            WHERE cs.requirement_id = :requirementId ;
            """, nativeQuery = true)
    List<ClassroomEntity> findByRequirements(@Param("requirementId") Integer requirementId);

}
