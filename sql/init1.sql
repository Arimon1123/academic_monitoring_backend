CREATE  DATABASE acad_monitoring;
\c acad_monitoring;
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-03-06 01:14:53.34

-- tables
-- Table: acad_user
CREATE TABLE acad_user (
                           id serial  NOT NULL,
                           username varchar(100)  NOT NULL,
                           password varchar(200)  NOT NULL,
                           status int  NOT NULL,
                           image_id int  NULL,
                           CONSTRAINT acad_user_pk PRIMARY KEY (id)
);

-- Table: activity
CREATE TABLE activity (
                          id serial  NOT NULL,
                          name varchar(150)  NOT NULL,
                          value int  NOT NULL,
                          dimension int  NOT NULL,
                          bimester int  NOT NULL,
                          status int  NOT NULL,
                          class_has_subject_id int  NOT NULL,
                          CONSTRAINT activity_pk PRIMARY KEY (id)
);

-- Table: activity_has_grade
CREATE TABLE activity_has_grade (
                                    id serial  NOT NULL,
                                    grade int  NOT NULL,
                                    status int  NOT NULL,
                                    activity_id int  NOT NULL,
                                    student_id int  NOT NULL,
                                    CONSTRAINT activity_has_grade_pk PRIMARY KEY (id)
);

-- Table: administrative
CREATE TABLE administrative (
                                id serial  NOT NULL,
                                person_id int  NOT NULL,
                                status int  NOT NULL,
                                CONSTRAINT administrative_pk PRIMARY KEY (id)
);

-- Table: attendance
CREATE TABLE attendance (
                            id serial  NOT NULL,
                            attendance int  NOT NULL,
                            date date  NOT NULL,
                            estudiante_id int  NOT NULL,
                            class_has_subject_id int  NOT NULL,
                            CONSTRAINT attendance_pk PRIMARY KEY (id)
);

-- Table: class
CREATE TABLE class (
                       id serial  NOT NULL,
                       year int  NOT NULL,
                       shift int  NOT NULL,
                       identifier varchar(20)  NOT NULL,
                       status int  NOT NULL,
                       grade_id int  NOT NULL,
                       CONSTRAINT class_pk PRIMARY KEY (id)
);

-- Table: class_has_subject
CREATE TABLE class_has_subject (
                                   id serial  NOT NULL,
                                   subject_id int  NOT NULL,
                                   class_id int  NOT NULL,
                                   classroom_id int  NOT NULL,
                                   teacher_id int  NOT NULL,
                                   CONSTRAINT id PRIMARY KEY (id)
);

-- Table: classroom
CREATE TABLE classroom (
                           id serial  NOT NULL,
                           number int  NOT NULL,
                           block varchar(15)  NOT NULL,
                           type varchar(100)  NOT NULL,
                           CONSTRAINT classroom_pk PRIMARY KEY (id)
);

-- Table: classroom_requirement
CREATE TABLE classroom_requirement (
                                       id int  NOT NULL,
                                       classroom_id int  NOT NULL,
                                       requirement_id int  NOT NULL,
                                       CONSTRAINT classroom_requirement_pk PRIMARY KEY (id)
);

-- Table: grade
CREATE TABLE grade (
                       id serial  NOT NULL,
                       section varchar(20)  NOT NULL,
                       number varchar(20)  NOT NULL,
                       CONSTRAINT grade_pk PRIMARY KEY (id)
);

-- Table: image
CREATE TABLE image (
                       id serial  NOT NULL,
                       uuid varchar(50)  NOT NULL,
                       type varchar(20)  NOT NULL,
                       name varchar(100)  NOT NULL,
                       CONSTRAINT image_pk PRIMARY KEY (id)
);

-- Table: parent
CREATE TABLE parent (
                        id serial  NOT NULL,
                        person_id int  NOT NULL,
                        status int  NOT NULL,
                        CONSTRAINT parent_pk PRIMARY KEY (id)
);

-- Table: permission
CREATE TABLE permission (
                            id serial  NOT NULL,
                            date date  NOT NULL,
                            permission_start_date timestamp  NOT NULL,
                            permission_end_date timestamp  NOT NULL,
                            permission_status int  NOT NULL,
                            reason varchar(400)  NOT NULL,
                            status int  NOT NULL,
                            student_id int  NOT NULL,
                            CONSTRAINT permission_pk PRIMARY KEY (id)
);

-- Table: permission_image
CREATE TABLE permission_image (
                                  id int  NOT NULL,
                                  permission_id int  NOT NULL,
                                  image_id int  NOT NULL,
                                  CONSTRAINT permission_image_pk PRIMARY KEY (id)
);

-- Table: person
CREATE TABLE person (
                        id serial  NOT NULL,
                        name varchar(100)  NOT NULL,
                        lastname varchar(100)  NOT NULL,
                        ci varchar(20)  NOT NULL,
                        phone varchar(20)  NOT NULL,
                        email varchar(100)  NOT NULL,
                        address varchar(200)  NOT NULL,
                        status int  NOT NULL,
                        acad_user_id int  NOT NULL,
                        CONSTRAINT person_pk PRIMARY KEY (id)
);

-- Table: reject
CREATE TABLE reject (
                        id int  NOT NULL,
                        reason varchar(400)  NOT NULL,
                        permission_id int  NOT NULL,
                        CONSTRAINT reject_pk PRIMARY KEY (id)
);

-- Table: requirement
CREATE TABLE requirement (
                             id serial  NOT NULL,
                             requirement varchar(100)  NOT NULL,
                             description varchar(400)  NOT NULL,
                             CONSTRAINT requirement_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
                      id serial  NOT NULL,
                      name varchar(100)  NOT NULL,
                      CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: schedule
CREATE TABLE schedule (
                          id serial  NOT NULL,
                          weekday int  NOT NULL,
                          start_time time  NOT NULL,
                          end_time time  NOT NULL,
                          class_has_subject_id int  NOT NULL,
                          CONSTRAINT schedule_pk PRIMARY KEY (id)
);

-- Table: student
CREATE TABLE student (
                         id serial  NOT NULL,
                         name varchar(150)  NOT NULL,
                         ci varchar(20)  NOT NULL,
                         father_lastname varchar(100)  NULL,
                         mother_lastname varchar(100)  NULL,
                         birthdate date  NOT NULL,
                         rude varchar(20)  NOT NULL,
                         address varchar(200)  NOT NULL,
                         status int  NOT NULL,
                         CONSTRAINT student_pk PRIMARY KEY (id)
);

-- Table: student_class
CREATE TABLE student_class (
                               id serial  NOT NULL,
                               student_id int  NOT NULL,
                               class_id int  NOT NULL,
                               CONSTRAINT student_class_pk PRIMARY KEY (id)
);

-- Table: student_parent
CREATE TABLE student_parent (
                                id serial  NOT NULL,
                                student_id int  NOT NULL,
                                parent_id int  NOT NULL,
                                CONSTRAINT student_parent_pk PRIMARY KEY (id)
);

-- Table: subject
CREATE TABLE subject (
                         id serial  NOT NULL,
                         name varchar(100)  NOT NULL,
                         hours int  NOT NULL,
                         status int  NOT NULL,
                         grade_id int  NOT NULL,
                         CONSTRAINT subject_pk PRIMARY KEY (id)
);

-- Table: subject_requirement
CREATE TABLE subject_requirement (
                                     id serial  NOT NULL,
                                     subject_id int  NOT NULL,
                                     requirements_id int  NOT NULL,
                                     CONSTRAINT subject_requirement_pk PRIMARY KEY (id)
);

-- Table: teacher
CREATE TABLE teacher (
                         id serial  NOT NULL,
                         person_id int  NOT NULL,
                         academic_email varchar(100)  NOT NULL,
                         status int  NOT NULL,
                         CONSTRAINT teacher_pk PRIMARY KEY (id)
);

-- Table: teacher_subject
CREATE TABLE teacher_subject (
                                 id serial  NOT NULL,
                                 subject_id int  NOT NULL,
                                 teacher_id int  NOT NULL,
                                 CONSTRAINT teacher_subject_pk PRIMARY KEY (id)
);

-- Table: user_roles
CREATE TABLE user_roles (
                            acad_user_id int  NOT NULL,
                            roles_id int  NOT NULL,
                            CONSTRAINT user_roles_pk PRIMARY KEY (acad_user_id,roles_id)
);

-- foreign keys
-- Reference: Table_36_classroom (table: classroom_requirement)
ALTER TABLE classroom_requirement ADD CONSTRAINT Table_36_classroom
    FOREIGN KEY (classroom_id)
        REFERENCES classroom (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Table_36_requirement (table: classroom_requirement)
ALTER TABLE classroom_requirement ADD CONSTRAINT Table_36_requirement
    FOREIGN KEY (requirement_id)
        REFERENCES requirement (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: Table_38_student (table: student_parent)
ALTER TABLE student_parent ADD CONSTRAINT Table_38_student
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: actividad_class_has_subject (table: activity)
ALTER TABLE activity ADD CONSTRAINT actividad_class_has_subject
    FOREIGN KEY (class_has_subject_id)
        REFERENCES class_has_subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: activity_has_grade_activity (table: activity_has_grade)
ALTER TABLE activity_has_grade ADD CONSTRAINT activity_has_grade_activity
    FOREIGN KEY (activity_id)
        REFERENCES activity (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: activity_has_grade_student (table: activity_has_grade)
ALTER TABLE activity_has_grade ADD CONSTRAINT activity_has_grade_student
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: administrative_person (table: administrative)
ALTER TABLE administrative ADD CONSTRAINT administrative_person
    FOREIGN KEY (person_id)
        REFERENCES person (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: asistencia_estudiante (table: attendance)
ALTER TABLE attendance ADD CONSTRAINT asistencia_estudiante
    FOREIGN KEY (estudiante_id)
        REFERENCES student (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: attendance_class_has_subject (table: attendance)
ALTER TABLE attendance ADD CONSTRAINT attendance_class_has_subject
    FOREIGN KEY (class_has_subject_id)
        REFERENCES class_has_subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: class_grade (table: class)
ALTER TABLE class ADD CONSTRAINT class_grade
    FOREIGN KEY (grade_id)
        REFERENCES grade (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_class (table: class_has_subject)
ALTER TABLE class_has_subject ADD CONSTRAINT class_has_subject_class
    FOREIGN KEY (class_id)
        REFERENCES class (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_classroom (table: class_has_subject)
ALTER TABLE class_has_subject ADD CONSTRAINT class_has_subject_classroom
    FOREIGN KEY (classroom_id)
        REFERENCES classroom (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_subject (table: class_has_subject)
ALTER TABLE class_has_subject ADD CONSTRAINT class_has_subject_subject
    FOREIGN KEY (subject_id)
        REFERENCES subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_teacher (table: class_has_subject)
ALTER TABLE class_has_subject ADD CONSTRAINT class_has_subject_teacher
    FOREIGN KEY (teacher_id)
        REFERENCES teacher (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: curso_estudiante_class (table: student_class)
ALTER TABLE student_class ADD CONSTRAINT curso_estudiante_class
    FOREIGN KEY (class_id)
        REFERENCES class (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: curso_estudiante_student (table: student_class)
ALTER TABLE student_class ADD CONSTRAINT curso_estudiante_student
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: licencia_image_permission (table: permission_image)
ALTER TABLE permission_image ADD CONSTRAINT licencia_image_permission
    FOREIGN KEY (permission_id)
        REFERENCES permission (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: parent_person (table: parent)
ALTER TABLE parent ADD CONSTRAINT parent_person
    FOREIGN KEY (person_id)
        REFERENCES person (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: permission_student (table: permission)
ALTER TABLE permission ADD CONSTRAINT permission_student
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: person_acad_user (table: person)
ALTER TABLE person ADD CONSTRAINT person_acad_user
    FOREIGN KEY (acad_user_id)
        REFERENCES acad_user (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: profesor_person (table: teacher)
ALTER TABLE teacher ADD CONSTRAINT profesor_person
    FOREIGN KEY (person_id)
        REFERENCES person (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: reject_permission (table: reject)
ALTER TABLE reject ADD CONSTRAINT reject_permission
    FOREIGN KEY (permission_id)
        REFERENCES permission (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: schedule_class_has_subject (table: schedule)
ALTER TABLE schedule ADD CONSTRAINT schedule_class_has_subject
    FOREIGN KEY (class_has_subject_id)
        REFERENCES class_has_subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: student_parent_parent (table: student_parent)
ALTER TABLE student_parent ADD CONSTRAINT student_parent_parent
    FOREIGN KEY (parent_id)
        REFERENCES parent (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: subject_grade (table: subject)
ALTER TABLE subject ADD CONSTRAINT subject_grade
    FOREIGN KEY (grade_id)
        REFERENCES grade (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: subject_requirement_requirements (table: subject_requirement)
ALTER TABLE subject_requirement ADD CONSTRAINT subject_requirement_requirements
    FOREIGN KEY (requirements_id)
        REFERENCES requirement (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: subject_requirement_subject (table: subject_requirement)
ALTER TABLE subject_requirement ADD CONSTRAINT subject_requirement_subject
    FOREIGN KEY (subject_id)
        REFERENCES subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: teacher_subject_subject (table: teacher_subject)
ALTER TABLE teacher_subject ADD CONSTRAINT teacher_subject_subject
    FOREIGN KEY (subject_id)
        REFERENCES subject (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: teacher_subject_teacher (table: teacher_subject)
ALTER TABLE teacher_subject ADD CONSTRAINT teacher_subject_teacher
    FOREIGN KEY (teacher_id)
        REFERENCES teacher (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_roles_acad_user (table: user_roles)
ALTER TABLE user_roles ADD CONSTRAINT user_roles_acad_user
    FOREIGN KEY (acad_user_id)
        REFERENCES acad_user (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_roles_roles (table: user_roles)
ALTER TABLE user_roles ADD CONSTRAINT user_roles_roles
    FOREIGN KEY (roles_id)
        REFERENCES role (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.

INSERT INTO acad_user values (999, 'admin', '$2a$12$Mgq.HqqQl1sCqEpvYFf80uXOWCml.9C/eX4TYxh30.XTmLAQXf9xC', 1,  null);
Insert into role values (1, 'ADMINISTRATIVE'), (2, 'TEACHER'), (3, 'PARENT');
Insert into user_roles values (999, 1), (999, 3);
INSERT INTO person values (999, 'Administrador', 'Administrador', '12345678', '3697984' ,'','', 1, 999);
insert into administrative values (999, 999 ,1 );
Insert into grade values (1, 'Primaria', '1'), (2, 'Primaria', '2'), (3, 'Primaria', '3'), (4, 'Primaria', '4'), (5, 'Primaria', '5'), (6, 'Primaria', '6'), (7, 'Secundaria', '1'), (8, 'Secundaria', '2'), (9, 'Secundaria', '3'), (10, 'Secundaria', '4'), (11, 'Secundaria', '5'), (12, 'Secundaria', '6');
Insert into subject values (1,'Matemática',2,1,1),(2,'Lenguaje',2,1,1),(3,'Ciencias Naturales',2,1,1),(4,'Ciencias Sociales',2,1,1),(5,'Educación Física',2,1,1),(6,'Educación Artística',2,1,1),(7,'Educación Religiosa',2,1,1),(8,'Inglés',2,1,1),(9,'Matemática',2,1,2),(10,'Lenguaje',2,1,2),(11,'Ciencias Naturales',2,1,2),(12,'Ciencias Sociales',2,1,2),(13,'Educación Física',2,1,2),(14,'Educación Artística',2,1,2),(15,'Educación Religiosa',2,1,2),(16,'Inglés',2,1,2),(17,'Matemática',2,1,3),(18,'Lenguaje',2,1,3),(19,'Ciencias Naturales',2,1,3),(20,'Ciencias Sociales',2,1,3),(21,'Educación Física',2,1,3),(22,'Educación Artística',2,1,3),(23,'Educación Religiosa',2,1,3);
Insert into class values (1,2024,1,'A',1,1);
Insert into class values (2,2024,1,'B',1,1);
Insert into class values (3,2024,1,'C',1,1);
Insert into class values (4,2024,1,'D',1,1);
Insert into class values (5,2024,1,'E',1,1);
Insert into class values (6,2024,1,'A',1,2);
Insert into class values (7,2024,1,'B',1,2);
Insert into class values (8,2024,1,'C',1,2);
Insert into class values (9,2024,1,'D',1,2);
Insert into class values (10,2024,1,'E',1,2);
Insert into class values (11,2024,1,'A',1,3);
Insert into class values (12,2024,1,'B',1,3);
insert into acad_user values (4, 'juan', '$2a$12$Mgq.HqqQl1sCqEpvYFf80uXOWCml.9C/eX4TYxh30.XTmLAQXf9xC', 1,  null);
insert into person values (4,'Juan','Gomez','12345678','2000-01-01','a@a.com','Calle 1',1,4);
insert into parent values (1,4,1);
Insert into student values (1,'Juan','12345678','Perez','Gomez','2000-01-01','123456','Calle 1',1);
insert into student_parent values (1,1,1);
Insert into student_class values (1,1,1);