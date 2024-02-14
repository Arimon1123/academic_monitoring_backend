package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer>{
    PersonEntity findByCi(String ci);
}
