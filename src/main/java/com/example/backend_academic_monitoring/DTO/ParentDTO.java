package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParentDTO{
    private Integer parentId;
    private PersonDTO person;
    private Integer status;
}
