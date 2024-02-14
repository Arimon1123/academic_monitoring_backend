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
    private String lastname;
    private String ci;
    private String address;
    private String email;
    private String phone;
}
