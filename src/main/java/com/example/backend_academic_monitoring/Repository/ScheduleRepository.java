package com.example.backend_academic_monitoring.Repository;


import com.example.backend_academic_monitoring.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer>{

    @Query(value = """
            SELECT s.id, s.end_time, s.start_time, s.weekday from\s
            schedule s join class_has_subject cs on s.class_has_subject_id = cs.id\s
            where cs.class_id = :classId""", nativeQuery = true)
    List<ScheduleEntity> findAllByClassId(@Param("classId") Integer class_id);
}
