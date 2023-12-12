package com.example.backend_academic_monitoring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastname;
    private String address;
    private String email;
    private String phone;
    private Integer status;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    List<SubjectEntity> subject;
    @Column(name = "acad_user_id")
    private Integer userId;
}
