package com.example.backend_academic_monitoring.DTO;

import com.example.backend_academic_monitoring.Entity.RoleEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDataDTO extends PersonDTO {
    private String imageUrl;
    private String username;
    private List<RoleEntity> roles;
    private String ci;
    private Integer status;

}
