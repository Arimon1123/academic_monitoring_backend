package com.example.backend_academic_monitoring.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeacherDTO {
    Integer id;
    String name;
    String lastname;
    String email;
    String phone;
    String address;
    Integer userId;
}
