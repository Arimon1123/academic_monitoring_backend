-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-29 22:20:20.114

-- tables
-- Table: actividad
CREATE  DATABASE acad_monitoring;
\c acad_monitoring;
CREATE TABLE actividad (
    id serial  NOT NULL,
    nombre varchar(150)  NOT NULL,
    valor int  NOT NULL,
    dimension int  NOT NULL,
    bimestre int  NOT NULL,
    curso_materia_id int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT actividad_pk PRIMARY KEY (id)
);

-- Table: administrativo
CREATE TABLE administrativo (
    id serial  NOT NULL,
    nombre varchar(100)  NOT NULL,
    apellido varchar(100)  NOT NULL,
    telefono varchar(20)  NOT NULL,
    email varchar(100)  NOT NULL,
    direccion varchar(200)  NOT NULL,
    id_usuario int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT administrativo_pk PRIMARY KEY (id)
);

-- Table: asistencia
CREATE TABLE asistencia (
    id serial  NOT NULL,
    asistencia int  NOT NULL,
    fecha date  NOT NULL,
    curso_materia_id int  NOT NULL,
    estudiante_id int  NOT NULL,
    CONSTRAINT asistencia_pk PRIMARY KEY (id)
);

-- Table: aula
CREATE TABLE aula (
    id serial  NOT NULL,
    numero int  NOT NULL,
    bloque varchar(15)  NOT NULL,
    tipo varchar(100)  NOT NULL,
    CONSTRAINT aula_pk PRIMARY KEY (id)
);

-- Table: aula_caracteristica
CREATE TABLE aula_caracteristica (
    id serial  NOT NULL,
    aula_id int  NOT NULL,
    caracteristicas_id int  NOT NULL,
    CONSTRAINT aula_caracteristica_pk PRIMARY KEY (id)
);

-- Table: caracteristicas
CREATE TABLE caracteristicas (
    id serial  NOT NULL,
    caracteristica varchar(150)  NOT NULL,
    CONSTRAINT caracteristicas_pk PRIMARY KEY (id)
);

-- Table: curso
CREATE TABLE curso (
    id serial  NOT NULL,
    gestion int  NOT NULL,
    turno int  NOT NULL,
    identificador varchar(20)  NOT NULL,
    id_grado int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT curso_pk PRIMARY KEY (id)
);

-- Table: curso_estudiante
CREATE TABLE curso_estudiante (
    id serial  NOT NULL,
    id_estudiante int  NOT NULL,
    id_curso int  NOT NULL,
    CONSTRAINT curso_estudiante_pk PRIMARY KEY (id)
);

-- Table: curso_materia
CREATE TABLE curso_materia (
    id serial  NOT NULL,
    id_curso int  NOT NULL,
    id_profesor int  NOT NULL,
    id_materia int  NOT NULL,
    aula_id int  NOT NULL,
    CONSTRAINT id PRIMARY KEY (id)
);

-- Table: estudiante
CREATE TABLE estudiante (
    id serial  NOT NULL,
    nombre varchar(150)  NOT NULL,
    apellido_paterno varchar(100)  NOT NULL,
    apellido_materno varchar(100)  NOT NULL,
    fecha_nacimiento date  NOT NULL,
    direccion varchar(200)  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT estudiante_pk PRIMARY KEY (id)
);

-- Table: estudiante_padre
CREATE TABLE estudiante_padre (
    id serial  NOT NULL,
    id_estudiante int  NOT NULL,
    id_padre int  NOT NULL,
    CONSTRAINT estudiante_padre_pk PRIMARY KEY (id)
);

-- Table: grado
CREATE TABLE grado (
    id serial  NOT NULL,
    seccion varchar(100)  NOT NULL,
    numero int  NOT NULL,
    CONSTRAINT grado_pk PRIMARY KEY (id)
);

-- Table: horario
CREATE TABLE horario (
    id serial  NOT NULL,
    curso_materia_id int  NOT NULL,
    dia_semana int  NOT NULL,
    hora_inicio time  NOT NULL,
    hora_fin time  NOT NULL,
    CONSTRAINT horario_pk PRIMARY KEY (id)
);

-- Table: image
CREATE TABLE image (
    id serial  NOT NULL,
    uuid varchar(20)  NOT NULL,
    tipo varchar(20)  NOT NULL,
    nombre varchar(100)  NOT NULL,
    CONSTRAINT image_pk PRIMARY KEY (id)
);

-- Table: licencia
CREATE TABLE licencia (
    id serial  NOT NULL,
    fecha_envio date  NOT NULL,
    fecha_inicio_licencia timestamp  NOT NULL,
    fecha_fin_licencia timestamp  NOT NULL,
    padre_id int  NOT NULL,
    estudiante_id int  NOT NULL,
    estado int  NOT NULL,
    motivo varchar(400)  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT licencia_pk PRIMARY KEY (id)
);

-- Table: licencia_image
CREATE TABLE licencia_image (
    id int  NOT NULL,
    licencia_id int  NOT NULL,
    image_id int  NOT NULL,
    CONSTRAINT licencia_image_pk PRIMARY KEY (id)
);

-- Table: materia
CREATE TABLE materia (
    id serial  NOT NULL,
    nombre varchar(100)  NOT NULL,
    id_grado int  NOT NULL,
    horas int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT materia_pk PRIMARY KEY (id)
);

-- Table: materia_requisitos
CREATE TABLE materia_requisitos (
    id serial  NOT NULL,
    requisitos_id int  NOT NULL,
    materia_id int  NOT NULL,
    CONSTRAINT materia_requisitos_pk PRIMARY KEY (id)
);

-- Table: nota_actividad
CREATE TABLE nota_actividad (
    id serial  NOT NULL,
    id_estudiante int  NOT NULL,
    id_actividad int  NOT NULL,
    nota int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT nota_actividad_pk PRIMARY KEY (id)
);

-- Table: padre
CREATE TABLE padre (
    id serial  NOT NULL,
    nombre varchar(150)  NOT NULL,
    apellido varchar(150)  NOT NULL,
    direccion varchar(100)  NOT NULL,
    email varchar(100)  NOT NULL,
    telefono varchar(30)  NOT NULL,
    id_usuario int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT padre_pk PRIMARY KEY (id)
);

-- Table: profeso_materia
CREATE TABLE profeso_materia (
    id serial  NOT NULL,
    id_materia int  NOT NULL,
    id_profesor int  NOT NULL,
    CONSTRAINT profeso_materia_pk PRIMARY KEY (id)
);

-- Table: profesor
CREATE TABLE profesor (
    id serial  NOT NULL,
    nombre varchar(100)  NOT NULL,
    apellidos varchar(100)  NOT NULL,
    telefono varchar(20)  NOT NULL,
    email varchar(100)  NOT NULL,
    direccion varchar(200)  NOT NULL,
    creado timestamp  NOT NULL,
    actualizado timestamp  NOT NULL,
    id_usuario int  NOT NULL,
    status int  NOT NULL,
    CONSTRAINT profesor_pk PRIMARY KEY (id)
);

-- Table: rechazo
CREATE TABLE rechazo (
    id int  NOT NULL,
    justificacion varchar(400)  NOT NULL,
    solicitud_licencia_id int  NOT NULL,
    administrativo_id int  NOT NULL,
    CONSTRAINT rechazo_pk PRIMARY KEY (id)
);

-- Table: requisitos
CREATE TABLE requisitos (
    id serial  NOT NULL,
    requisito varchar(100)  NOT NULL,
    descripcion varchar(400)  NOT NULL,
    CONSTRAINT requisitos_pk PRIMARY KEY (id)
);

-- Table: usuario
CREATE TABLE usuario (
    id serial  NOT NULL,
    usuario varchar(100)  NOT NULL,
    password varchar(200)  NOT NULL,
    creado timestamp  NOT NULL,
    actualizado timestamp  NOT NULL,
    status int  NOT NULL,
    image_id int  NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Table_20_curso_materia (table: horario)
ALTER TABLE horario ADD CONSTRAINT Table_20_curso_materia
    FOREIGN KEY (curso_materia_id)
    REFERENCES curso_materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: actividad_curso_materia (table: actividad)
ALTER TABLE actividad ADD CONSTRAINT actividad_curso_materia
    FOREIGN KEY (curso_materia_id)
    REFERENCES curso_materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: adminitrative_user (table: administrativo)
ALTER TABLE administrativo ADD CONSTRAINT adminitrative_user
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: asistencia_curso_materia (table: asistencia)
ALTER TABLE asistencia ADD CONSTRAINT asistencia_curso_materia
    FOREIGN KEY (curso_materia_id)
    REFERENCES curso_materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: asistencia_estudiante (table: asistencia)
ALTER TABLE asistencia ADD CONSTRAINT asistencia_estudiante
    FOREIGN KEY (estudiante_id)
    REFERENCES estudiante (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: aula_caracteristica_aula (table: aula_caracteristica)
ALTER TABLE aula_caracteristica ADD CONSTRAINT aula_caracteristica_aula
    FOREIGN KEY (aula_id)
    REFERENCES aula (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: aula_caracteristica_caracteristicas (table: aula_caracteristica)
ALTER TABLE aula_caracteristica ADD CONSTRAINT aula_caracteristica_caracteristicas
    FOREIGN KEY (caracteristicas_id)
    REFERENCES caracteristicas (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: class_Grade (table: curso)
ALTER TABLE curso ADD CONSTRAINT class_Grade
    FOREIGN KEY (id_grado)
    REFERENCES grado (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_class (table: curso_materia)
ALTER TABLE curso_materia ADD CONSTRAINT class_has_subject_class
    FOREIGN KEY (id_curso)
    REFERENCES curso (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_subject (table: curso_materia)
ALTER TABLE curso_materia ADD CONSTRAINT class_has_subject_subject
    FOREIGN KEY (id_materia)
    REFERENCES materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: class_has_subject_teacher (table: curso_materia)
ALTER TABLE curso_materia ADD CONSTRAINT class_has_subject_teacher
    FOREIGN KEY (id_profesor)
    REFERENCES profesor (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: curso_materia_aula (table: curso_materia)
ALTER TABLE curso_materia ADD CONSTRAINT curso_materia_aula
    FOREIGN KEY (aula_id)
    REFERENCES aula (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: father_user (table: padre)
ALTER TABLE padre ADD CONSTRAINT father_user
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: grade_activitie_activities (table: nota_actividad)
ALTER TABLE nota_actividad ADD CONSTRAINT grade_activitie_activities
    FOREIGN KEY (id_actividad)
    REFERENCES actividad (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: grade_activitie_student (table: nota_actividad)
ALTER TABLE nota_actividad ADD CONSTRAINT grade_activitie_student
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiante (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: licencia_image_image (table: licencia_image)
ALTER TABLE licencia_image ADD CONSTRAINT licencia_image_image
    FOREIGN KEY (image_id)
    REFERENCES image (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: licencia_image_licencia (table: licencia_image)
ALTER TABLE licencia_image ADD CONSTRAINT licencia_image_licencia
    FOREIGN KEY (licencia_id)
    REFERENCES licencia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: materia_requisitos_materia (table: materia_requisitos)
ALTER TABLE materia_requisitos ADD CONSTRAINT materia_requisitos_materia
    FOREIGN KEY (materia_id)
    REFERENCES materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: materia_requisitos_requisitos (table: materia_requisitos)
ALTER TABLE materia_requisitos ADD CONSTRAINT materia_requisitos_requisitos
    FOREIGN KEY (requisitos_id)
    REFERENCES requisitos (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: motivo_rechazo_administrativo (table: rechazo)
ALTER TABLE rechazo ADD CONSTRAINT motivo_rechazo_administrativo
    FOREIGN KEY (administrativo_id)
    REFERENCES administrativo (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: motivo_rechazo_solicitud_licencia (table: rechazo)
ALTER TABLE rechazo ADD CONSTRAINT motivo_rechazo_solicitud_licencia
    FOREIGN KEY (solicitud_licencia_id)
    REFERENCES licencia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: solicitud_licencia_estudiante (table: licencia)
ALTER TABLE licencia ADD CONSTRAINT solicitud_licencia_estudiante
    FOREIGN KEY (estudiante_id)
    REFERENCES estudiante (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: solicitud_licencia_padre (table: licencia)
ALTER TABLE licencia ADD CONSTRAINT solicitud_licencia_padre
    FOREIGN KEY (padre_id)
    REFERENCES padre (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: student_class_class (table: curso_estudiante)
ALTER TABLE curso_estudiante ADD CONSTRAINT student_class_class
    FOREIGN KEY (id_curso)
    REFERENCES curso (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: student_class_student (table: curso_estudiante)
ALTER TABLE curso_estudiante ADD CONSTRAINT student_class_student
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiante (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: student_has_father_father (table: estudiante_padre)
ALTER TABLE estudiante_padre ADD CONSTRAINT student_has_father_father
    FOREIGN KEY (id_padre)
    REFERENCES padre (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: student_has_father_student (table: estudiante_padre)
ALTER TABLE estudiante_padre ADD CONSTRAINT student_has_father_student
    FOREIGN KEY (id_estudiante)
    REFERENCES estudiante (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: subject_Grade (table: materia)
ALTER TABLE materia ADD CONSTRAINT subject_Grade
    FOREIGN KEY (id_grado)
    REFERENCES grado (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: teacher_has_subject_subject (table: profeso_materia)
ALTER TABLE profeso_materia ADD CONSTRAINT teacher_has_subject_subject
    FOREIGN KEY (id_materia)
    REFERENCES materia (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: teacher_has_subject_teacher (table: profeso_materia)
ALTER TABLE profeso_materia ADD CONSTRAINT teacher_has_subject_teacher
    FOREIGN KEY (id_profesor)
    REFERENCES profesor (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: teacher_user (table: profesor)
ALTER TABLE profesor ADD CONSTRAINT teacher_user
    FOREIGN KEY (id_usuario)
    REFERENCES usuario (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

