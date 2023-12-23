package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    ImageEntity findByUuid(String uuid);
    @Query(value = "SELECT i FROM ImageEntity i, UserEntity u  " +
                    "WHERE i.id = u.imageId and  u.imageId = ?1")
    ImageEntity findByUserId(Integer userId);
}
