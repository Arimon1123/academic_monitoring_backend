package com.example.backend_academic_monitoring.DTO;

import jdk.jfr.Timespan;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GradeDTO {
    private Integer id;
    private String section;
    private String number;
    private Integer status;
}
