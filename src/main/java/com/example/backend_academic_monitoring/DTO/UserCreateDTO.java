package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    Integer id;
    String name;
    String lastname;
    String email;
    String academicEmail;
    String phone;
    String address;
    String ci;
    Integer userId;
    String username;
    String password;
    List<RoleEntity> roles;
}
