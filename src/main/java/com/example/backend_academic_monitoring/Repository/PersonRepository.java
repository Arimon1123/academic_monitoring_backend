package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{
    List<PersonEntity> findAllByCiIsLike(String ci);
    @Query(value = "SELECT p.id, name, lastname, ci, phone, email, address, p.status, acad_user_id " +
            "FROM person  p , acad_user u WHERE p.name LIKE %:name% " +
            "or p.lastname LIKE %:name% or p.ci LIKE %:name% and u.role = :role and u.id = p.acad_user_id"  , nativeQuery = true)
    List<PersonEntity> findAllByNameAndRole(@Param("name") String name , @Param("role") String role);
    @Query(value = "SELECT p.id, name, lastname, ci, phone, email, address, p.status, acad_user_id " +
            "FROM person  p , acad_user u WHERE  " +
            "u.role LIKE %:role% and u.id = p.acad_user_id"  , nativeQuery = true)
    List<PersonEntity> findAllByRole(String role);
    PersonEntity findByCi(String ci);
}
