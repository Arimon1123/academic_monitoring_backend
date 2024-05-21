package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    boolean existsByEmail(String email);

    @Query("SELECT s FROM StudentEntity s JOIN " +
            "ParentStudentEntity ps ON s.id = ps.student.id " +
            "WHERE ps.parent.id = :parentId AND s.status = 1")
    List<StudentEntity> findAllByParentId(Integer parentId);

    @Query(value = "Select s.id, s.name, s.address, s.birthdate, s.father_lastname , s.mother_lastname, " +
            "s.ci, s.rude, s.status" +
            " from student s join student_class sc on s.id = sc.student_id where" +
            " sc.class_id = :classId  order by s.father_lastname desc ", nativeQuery = true)
    List<StudentEntity> findAllByClassId(Integer classId);

    @Query(value = """
            Select s.id, s.name, s.address, s.birthdate, s.father_lastname , s.mother_lastname,s.email,
                    s.ci, s.rude, s.status, s.acad_user_id , username, au.status as userstatus , r.id as roleid, r.name as rolename
                    from student s join student_class sc on s.id = sc.student_id\s
                        join class_has_subject chs on sc.class_id = chs.class_id
                        left join public.acad_user au on s.acad_user_id = au.id
                        left join user_roles ur on au.id = ur.acad_user_id
                        left join role r on ur.roles_id = r.id
                    where chs.id = :assignationId  order by s.father_lastname , s.mother_lastname , s.name;""", nativeQuery = true)
    List<StudentEntity> findAllByAssignationId(Integer assignationId);

    @Query(value = """ 
            SELECT s FROM StudentEntity s
                        WHERE lower(s.ci) LIKE lower(:ci)
                        and lower(s.rude) LIKE lower(:rude)
                        and lower(s.name) LIKE lower(:name)
                        and (lower(s.fatherLastname) LIKE lower(:lastname)
                        or lower(s.motherLastname) LIKE lower(:lastname))""")
    Page<StudentEntity> searchStudent(String ci, String rude, String name, String lastname, Pageable pageable);

    StudentEntity findByUser_Id(Integer userId);

    @Query(value = """
             Select s.* from student s
             join student_class sc on sc.student_id = s.id
             join class c on c.id = sc.class_id
             where c.year = 2024 group by s, s.id, name, ci, father_lastname,
            mother_lastname, birthdate, rude, address, s.status, acad_user_id, email""",
            nativeQuery = true)
    List<StudentEntity> findAllByYear(Integer year);
}
