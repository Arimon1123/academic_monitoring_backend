package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.DTO.GradesDTO;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TotalGradesRepository {
    @Autowired
    private EntityManager entityManager;
    public List<GradesDTO> gradeList (Integer studentId, Integer year, Integer subjectId){
        Query query =  entityManager.createNativeQuery("select sum(grade * activity.value * dimension.value)/10000 as total_grade ,student_id, student.name, activity.bimester, subject.id as subject_id, subject.name as subject_name\n" +
                        "       from activity_has_grade\n" +
                        "           left join activity on activity_has_grade.activity_id = activity.id\n" +
                        "           join class_has_subject on activity.class_has_subject_id = class_has_subject.id\n" +
                        "           join class on class_has_subject.class_id = class.id\n" +
                        "           join student on activity_has_grade.student_id = student.id\n" +
                        "           join subject on class_has_subject.subject_id = subject.id\n" +
                        "           join dimension on activity.dimension = dimension.name\n" +
                        "       where student_id =?1 and subject_id = ?2 and class.year = ?3\n" +
                        "       group by student_id, student.name,activity.bimester, subject.name, subject.id;\n")
                .setParameter(1, studentId)
                .setParameter(3, year)
                .setParameter(2, subjectId);
        List<GradesDTO> list = query.getResultList().stream().map(
                result -> result[0]
        ).toList();
        )
    }
}
