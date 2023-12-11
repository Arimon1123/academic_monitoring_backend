package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.UserDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
