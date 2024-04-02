
select sum(grade * activity.value * dimension.value)/10000 as total_grade ,student_id, student.name, activity.bimester, subject.id as subject_id, subject.name as subject_name
from activity_has_grade
         left join activity on activity_has_grade.activity_id = activity.id
         join class_has_subject on activity.class_has_subject_id = class_has_subject.id
         join class on class_has_subject.class_id = class.id
         join student on activity_has_grade.student_id = student.id
         join subject on class_has_subject.subject_id = subject.id
         join dimension on activity.dimension = dimension.name
where student_id = 13 and subject_id = 1
group by student_id, student.name,activity.bimester, subject.name, subject.id;

insert into activity values (34, 'Tarea 6: Álgebra', 50, 'HACER', 3, 1, 1),
                            (35, 'Examen 4', 100, 'SABER', 3, 1, 1),
                            (36, 'Tarea 7: Ortografía', 50, 'HACER', 3, 1, 1),
                            (37, 'Tarea 3: Gramática', 100, 'DECIDIR', 3, 1, 1),
                            (38, 'Examen 3', 100, 'SER', 3, 1, 1),
                            (39, 'Actividad 3', 100, 'HACER', 4, 1, 1),
                            (40, 'Test 8', 100, 'SABER', 4, 1, 1),
                            (41, 'Test 9', 100, 'SER', 4, 1, 1),
                            (42, 'Test 10', 100, 'DECIDIR', 4, 1, 1);

-- Inserts para class_has_subject_id = 15
INSERT INTO activity (id, name, value, dimension, bimester, status, class_has_subject_id) VALUES
                                                                                              (43, 'Tarea 8: Historia', 50, 'HACER', 1, 1, 15),
                                                                                              (44, 'Examen 5', 100, 'SABER', 1, 1, 15),
                                                                                              (45, 'Tarea 9: Ciencias Naturales', 50, 'HACER', 1, 1, 15),
                                                                                              (46, 'Tarea 4: Física', 100, 'DECIDIR', 1, 1, 15),
                                                                                              (47, 'Examen 4', 100, 'SER', 1, 1, 15),
                                                                                              (48, 'Actividad 4', 100, 'HACER', 2, 1, 15),
                                                                                              (49, 'Test 11', 100, 'SABER', 2, 1, 15),
                                                                                              (50, 'Test 12', 100, 'SER', 2, 1, 15),
                                                                                              (51, 'Test 13', 100, 'DECIDIR', 2, 1, 15),
                                                                                              (52, 'Tarea 10: Geografía', 50, 'HACER', 3, 1, 15),
                                                                                              (53, 'Examen 6', 100, 'SABER', 3, 1, 15),
                                                                                              (54, 'Tarea 11: Química', 50, 'HACER', 3, 1, 15),
                                                                                              (55, 'Tarea 5: Biología', 100, 'DECIDIR', 3, 1, 15),
                                                                                              (56, 'Examen 5', 100, 'SER', 3, 1, 15),
                                                                                              (57, 'Actividad 5', 100, 'HACER', 4, 1, 15),
                                                                                              (58, 'Test 14', 100, 'SABER', 4, 1, 15),
                                                                                              (59, 'Test 15', 100, 'SER', 4, 1, 15),
                                                                                              (60, 'Test 16', 100, 'DECIDIR', 4, 1, 15);

-- Inserts para class_has_subject_id = 16
INSERT INTO activity (id, name, value, dimension, bimester, status, class_has_subject_id) VALUES
                                                                                              (61, 'Tarea 12: Educación Física', 50, 'HACER', 1, 1, 16),
                                                                                              (62, 'Examen 7', 100, 'SABER', 1, 1, 16),
                                                                                              (63, 'Tarea 13: Arte', 50, 'HACER', 1, 1, 16),
                                                                                              (64, 'Tarea 6: Música', 100, 'DECIDIR', 1, 1, 16),
                                                                                              (65, 'Examen 6', 100, 'SER', 1, 1, 16),
                                                                                              (66, 'Actividad 6', 100, 'HACER', 2, 1, 16),
                                                                                              (67, 'Test 17', 100, 'SABER', 2, 1, 16),
                                                                                              (68, 'Test 18', 100, 'SER', 2, 1, 16),
                                                                                              (69, 'Test 19', 100, 'DECIDIR', 2, 1, 16),
                                                                                              (70, 'Tarea 14: Tecnología', 50, 'HACER', 3, 1, 16),
                                                                                              (71, 'Examen 8', 100, 'SABER', 3, 1, 16),
                                                                                              (72, 'Tarea 15: Inglés', 50, 'HACER', 3, 1, 16),
                                                                                              (73, 'Tarea 7: Francés', 100, 'DECIDIR', 3, 1, 16),
                                                                                              (74, 'Examen 7', 100, 'SER', 3, 1, 16),
                                                                                              (75, 'Actividad 7', 100, 'HACER', 4, 1, 16),
                                                                                              (76, 'Test 20', 100, 'SABER', 4, 1, 16),
                                                                                              (77, 'Test 21', 100, 'SER', 4, 1, 16),
                                                                                              (78, 'Test 22', 100, 'DECIDIR', 4, 1, 16);
INSERT INTO activity_has_grade (id, grade, status, activity_id, student_id) VALUES

                                                                                (10, 100, 1, 34, 13),
                                                                                (11, 100, 1, 35, 13),
                                                                                (12, 100, 1, 36, 13),
                                                                                (13, 100, 1, 37, 13),
                                                                                (14, 100, 1, 38, 13),
                                                                                (15, 100, 1, 39, 13),
                                                                                (16, 100, 1, 40, 13),
                                                                                (17, 100, 1, 41, 13),
                                                                                (18, 100, 1, 42, 13),
                                                                                (19, 100, 1, 43, 13),
                                                                                (20, 100, 1, 44, 13),
                                                                                (21, 100, 1, 45, 13),
                                                                                (22, 100, 1, 46, 13),
                                                                                (23, 100, 1, 47, 13),
                                                                                (24, 100, 1, 48, 13),
                                                                                (25, 100, 1, 49, 13),
                                                                                (26, 100, 1, 50, 13),
                                                                                (27, 100, 1, 51, 13),
                                                                                (28, 100, 1, 52, 13),
                                                                                (29, 100, 1, 53, 13),
                                                                                (30, 100, 1, 54, 13),
                                                                                (31, 100, 1, 55, 13),
                                                                                (32, 100, 1, 56, 13),
                                                                                (33, 100, 1, 57, 13),
                                                                                (34, 100, 1, 58, 13),
                                                                                (35, 100, 1, 59, 13),
                                                                                (36, 100, 1, 60, 13),
                                                                                (37, 100, 1, 61, 13),
                                                                                (38, 100, 1, 62, 13),
                                                                                (39, 100, 1, 63, 13),
                                                                                (40, 100, 1, 64, 13),
                                                                                (41, 100, 1, 65, 13),
                                                                                (42, 100, 1, 66, 13),
                                                                                (43, 100, 1, 67, 13),
                                                                                (44, 100, 1, 68, 13),
                                                                                (45, 100, 1, 69, 13),
                                                                                (46, 100, 1, 70, 13),
                                                                                (47, 100, 1, 71, 13),
                                                                                (48, 100, 1, 72, 13),
                                                                                (49, 100, 1, 73, 13),
                                                                                (50, 100, 1, 74, 13),
                                                                                (51, 100, 1, 75, 13),
                                                                                (52, 100, 1, 76, 13),
                                                                                (53, 100, 1, 77, 13),
                                                                                (54, 100, 1, 78, 13);


