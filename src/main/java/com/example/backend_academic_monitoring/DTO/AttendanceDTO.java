package com.example.backend_academic_monitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceDTO {
    private Integer id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

}
