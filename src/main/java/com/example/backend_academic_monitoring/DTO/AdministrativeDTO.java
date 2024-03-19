package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdministrativeDTO {
    private Integer id;
    private PersonDTO person;
    private Integer status;
}
