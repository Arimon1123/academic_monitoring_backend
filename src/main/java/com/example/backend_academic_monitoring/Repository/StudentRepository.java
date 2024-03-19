package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    StudentEntity findByIdAndStatus(Integer id, Integer status);
    List<StudentEntity> findAllByStatus(Integer status);
    boolean existsByCi(String ci);
    boolean existsByRude(String rude);
    @Query("SELECT s FROM StudentEntity s JOIN " +
            "ParentStudentEntity ps ON s.id = ps.student.id " +
            "WHERE ps.father.id = :parentId AND s.status = 1")
    List<StudentEntity> findAllByParentId(Integer parentId);
}
