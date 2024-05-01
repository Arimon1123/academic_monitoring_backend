package com.example.backend_academic_monitoring.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ReportRepository {

    private static final Logger log = LoggerFactory.getLogger(ReportRepository.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getPerformanceReport(Integer gradeId) {
        Query query = entityManager.createNativeQuery("select  row_number() over (),round(sum(total_grade)/count(student_id),2) as grade, class_id, year, shift, identifier, grade_number,bimester\n" +
                "from (select sum(grade * activity.value * dimension.value)/10000 as total_grade ,\n" +
                "             count(student_id) as student_id, class.id as class_id, class.year as year,\n" +
                "             class.shift as shift, class.identifier as identifier, grade.number as grade_number, grade.section as grade_section , bimester\n" +
                "      from activity_has_grade\n" +
                "               left join activity on activity_has_grade.activity_id = activity.id\n" +
                "               join class_has_subject on activity.class_has_subject_id = class_has_subject.id\n" +
                "               join class on class_has_subject.class_id = class.id\n" +
                "               join student on activity_has_grade.student_id = student.id\n" +
                "               join subject on class_has_subject.subject_id = subject.id\n" +
                "               join dimension on activity.dimension = dimension.name\n" +
                "               join grade on class.grade_id = grade.id\n" +
                "      where grade.id = :gradeId\n" +
                "      group by student_id,bimester, class_has_subject.id, class.id, year, shift, identifier, grade_number, grade_section) as t\n" +
                "group by class_id, year, shift, identifier, grade_number, grade_section , bimester order by bimester, class_id;");
        query.setParameter("gradeId", gradeId);
        return query.getResultList();
    }

    public List<Object[]> getAttendanceReport(Date startDate, Date finalDate, Integer gradeId) {
        Query query = entityManager.createNativeQuery("select count(a.attendance)/(cast(num_dates.num_dates * num_subjects.num_subjects as float)) as attendance_avg,\n" +
                "                       count(a.attendance) , num_dates as clases, num_subjects  as subjects,\n" +
                "                       a.attendance , cl.year, cl.shift, cl.identifier, g.section, g.number, g.id\n" +
                "                from attendance a\n" +
                "                         join class_has_subject c on a.class_has_subject_id = c.id\n" +
                "                         join class cl on c.class_id = cl.id\n" +
                "                         join grade g on cl.grade_id = g.id,\n" +
                "                        lateral (SELECT COUNT(DISTINCT date) AS num_dates\n" +
                "                            FROM attendance a left join class_has_subject chs on chs.id = a.class_has_subject_id\n" +
                "                            join class c on c.id = chs.class_id join grade g1 on c.grade_id = g1.id WHERE g1.id = g.id and a.date between :startDate and :finalDate) as num_dates,\n" +
                "                        Lateral (Select count(subject.id) as num_subjects from subject where subject.grade_id = g.id) as num_subjects\n" +
                "                where g.id = :gradeId and a.date between :startDate and :finalDate\n" +
                "                group by  a.attendance, cl.year, cl.shift, cl.identifier, g.section, g.number, g.id, num_dates.num_dates, num_subjects.num_subjects\n" +
                "                order by  number,identifier,a.attendance;");
        query.setParameter("startDate", startDate).setParameter("finalDate", finalDate).setParameter("gradeId", gradeId);
        return query.getResultList();
    }

    public List<Object[]> getGradeRangesReport(Integer bimester, Integer gradeId) {
        Query query = entityManager.createNativeQuery(""" 
                Select t.range  , count(*) as count, sum(count(*)) over () as total
                  from (
                   Select case
                      when grade between 0 and 9 then '0-9'
                      when grade between 10 and 19 then '10-19'
                      when grade between 20 and 29 then '20-29'
                      when grade between 30 and 39 then '30-39'
                      when grade between 40 and 49 then '40-49'
                      when grade between 50 and 59 then '50-59'
                     when grade between 60 and 69 then '60-69'
                     when grade between 70 and 79 then '70-79'
                     when grade between 80 and 89 then '80-89'
                     when grade between 90 and 100 then '90-100'
                     else 'No grade' end as range , activity_has_grade.grade
                     from activity_has_grade join activity on activity_has_grade.activity_id = activity.id
                     join class_has_subject on activity.class_has_subject_id = class_has_subject.id
                     join class on class_has_subject.class_id = class.id
                     where bimester = :bimester and class.grade_id = :gradeId
                     ) as t group by t.range order by t.range;""");
        query.setParameter("bimester", bimester).setParameter("gradeId", gradeId);
        return query.getResultList();
    }
}
