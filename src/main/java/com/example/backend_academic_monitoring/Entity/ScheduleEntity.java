package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weekday;
    private Time start_time;
    private Time end_time;
}
