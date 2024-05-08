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
            "WHERE ps.parent.id = :parentId AND s.status = 1")
    List<StudentEntity> findAllByParentId(Integer parentId);

    @Query(value = "Select s.id, s.name, s.address, s.birthdate, s.father_lastname , s.mother_lastname, " +
            "s.ci, s.rude, s.status" +
            " from student s join student_class sc on s.id = sc.student_id where " +
            " sc.class_id = :classId  order by s.father_lastname desc ", nativeQuery = true)
    List<StudentEntity> findAllByClassId(Integer classId);

    @Query(value = "Select s.id, s.name, s.address, s.birthdate, s.father_lastname , s.mother_lastname,\n" +
            "        s.ci, s.rude, s.status\n" +
            "        from student s join student_class sc on s.id = sc.student_id join class_has_subject chs on sc.class_id = chs.class_id\n" +
            "        where chs.id = :assignationId  order by s.father_lastname;", nativeQuery = true)
    List<StudentEntity> findAllByAssignationId(Integer assignationId);

    @Query(value = "SELECT s FROM StudentEntity s " +
            "WHERE s.ci ILIKE %:ci% OR s.rude ILIKE %:rude% " +
            "OR s.name ILIKE %:name% OR s.fatherLastname ILIKE %:fatherLastname% OR " +
            "s.motherLastname ILIKE %:motherLastname%")
    List<StudentEntity> searchStudent(String ci, String rude, String name, String fatherLastname, String motherLastname);
}
