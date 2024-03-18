package com.example.backend_academic_monitoring.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionDTO {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/La_Paz")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/La_Paz")
    private Timestamp permissionStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/La_Paz")
    private Timestamp permissionEndDate;
    private String reason;
    private Integer permissionStatus;
    private List<String> images;
    private StudentDTO student;
}
