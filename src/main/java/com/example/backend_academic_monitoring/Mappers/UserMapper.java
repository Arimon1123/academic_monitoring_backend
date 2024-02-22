package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.UserDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;

public class UserMapper {
    public static UserDTO entityToDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        return userDTO;

    }
}
