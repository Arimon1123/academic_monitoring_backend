package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    List<PermissionEntity> findAllByPermissionStatusAndDateAfter(Integer permissionStatus, Date date);

    @Query(value = """
            SELECT p.id , p.date , p.permission_end_date, p.permission_start_date, p.permission_status, p.reason , p.status , p.student_id
            FROM permission p join student s on p.student_id = s.id
            join student_class sc on sc.student_id = s.id
            where sc.class_id = :classId and :date between p.permission_start_date and p.permission_end_date and p.permission_status = :status """, nativeQuery = true)
    List<PermissionEntity> findAllByClassIdAndDate(Integer classId, Timestamp date, Integer status);

    @Query(value = """
            SELECT p.*
             FROM permission p
             where p.student_id in :studentIds and p.date < :date""", nativeQuery = true)
    List<PermissionEntity> findAllByStudentIds(List<Integer> studentIds, Timestamp date);


}
