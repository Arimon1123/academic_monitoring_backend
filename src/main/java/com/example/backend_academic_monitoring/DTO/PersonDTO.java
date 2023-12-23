package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonDTO {
    private Integer id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String lastname;
    private Integer status;
}
