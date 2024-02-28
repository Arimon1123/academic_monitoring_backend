package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
   UserEntity findByUsername(String username);
   UserEntity findByUsernameAndStatus(String username, Integer status);
   boolean existsByUsername(String username);
   @Query("SELECT u FROM UserEntity u , PersonEntity p WHERE u.id = p.userId  and p.id= :id")
   UserEntity findByPersonId(Integer id);
}
