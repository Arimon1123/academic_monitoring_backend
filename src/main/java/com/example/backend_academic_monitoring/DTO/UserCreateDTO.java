package com.example.backend_academic_monitoring.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    Integer id;
    String name;
    String lastname;
    String email;
    String phone;
    String address;
    Integer userId;
    String username;
    String password;
    String role;
}
