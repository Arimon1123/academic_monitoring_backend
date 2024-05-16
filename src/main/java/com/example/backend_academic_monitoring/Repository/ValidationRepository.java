package com.example.backend_academic_monitoring.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ValidationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> findUnterminatedSubjects(Integer bimester, Integer year) {
        Query query = entityManager.createNativeQuery("""
                select activity.bimester, subject.id as subject_id,
                subject.name as subject_name, class_has_subject.id as assignation_id,
                person.name, person.lastname, grade.number, grade.section, c.identifier,c.shift
                from activity_has_grade
                left join activity on activity_has_grade.activity_id = activity.id
                join class_has_subject on activity.class_has_subject_id = class_has_subject.id
                join teacher on class_has_subject.teacher_id = teacher.id
                join person on teacher.person_id = person.id
                join class c on class_has_subject.class_id = c.id
                join grade on c.grade_id = grade.id
                join subject on class_has_subject.subject_id = subject.id
                join dimension on activity.dimension = dimension.name
                , LATERAL (
                Select count(student.id ) from student
                join student_class on student.id = student_class.student_id
                where student_class.class_id = c.id) as count
                where  c.year = :year and bimester = :bimester and activity_has_grade.status = 1
                group by activity.bimester, subject.name, subject.id, class_has_subject.id, count.count,
                person.name, person.lastname,grade.number, grade.section, c.identifier,c.shift
                having sum(activity.value * dimension.value)/(100 * count.count) != 100;""");
        query.setParameter("year", year).setParameter("bimester", bimester);
        return query.getResultList();
    }

}
