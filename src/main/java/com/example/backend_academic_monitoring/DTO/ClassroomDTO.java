package com.example.backend_academic_monitoring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO {
    private Integer id;
    private Integer number;
    private String block;
    private String type;
}
