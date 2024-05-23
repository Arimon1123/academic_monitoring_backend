package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    List<PersonEntity> findAllByCiIsLike(String ci);

    @Query(value = """
            SELECT p.id, p.name, p.lastname, p.ci, p.phone, p.email, p.address, p.status, p.acad_user_id
            FROM person p
            JOIN acad_user u ON u.id = p.acad_user_id
            JOIN user_roles ur ON ur.acad_user_id = u.id
            JOIN role r ON ur.roles_id = r.id
            WHERE lower(p.name) LIKE lower(:name)
            AND lower(p.lastname) LIKE lower(:lastname)
            AND lower(p.ci) LIKE lower(:ci)
            AND lower(r.name) LIKE lower(:role)
            GROUP BY p.id
            ORDER BY p.id;""", nativeQuery = true)
    Page<PersonEntity> findAllByNameAndRole(@Param("name") String name,
                                            @Param("lastname") String lastname,
                                            @Param("ci") String ci,
                                            @Param("role") String role,
                                            Pageable pageable);

    @Query(value = """
            SELECT p.id, p.name, p.lastname, p.ci, p.phone, p.email, p.address, p.status, p.acad_user_id 
            FROM person p 
            JOIN acad_user u ON u.id = p.acad_user_id 
            JOIN user_roles ur ON ur.acad_user_id = u.id 
            JOIN role r ON ur.roles_id = r.id 
            WHERE r.name = :role
            GROUP BY p.id
            ORDER BY p.id;""", nativeQuery = true)
    List<PersonEntity> findAllByRole(String role);

    PersonEntity findByCi(String ci);

    boolean existsByCi(String ci);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query(value = "SELECT * FROM person WHERE acad_user_id = :userId", nativeQuery = true)
    PersonEntity findByUserId(@Param("userId") Integer userId);
}
