package com.example.backend_academic_monitoring.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentCreateDTO extends StudentDTO {
    private Integer classId;
    private List<Integer> parentId;
    private boolean isUser;
}
