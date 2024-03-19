package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<ParentEntity, Integer>{
    List<ParentEntity> findAllByPersonCiContainingAndStatus(String ci, Integer status);
    ParentEntity findByPerson_UserId(Integer userId);
}
