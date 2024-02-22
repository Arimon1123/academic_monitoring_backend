package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.RoleEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private List<RoleEntity> role;
}
