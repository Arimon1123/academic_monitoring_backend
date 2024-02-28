package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
import com.example.backend_academic_monitoring.DTO.UserDTO;
import com.example.backend_academic_monitoring.DTO.UserDataDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;

public class UserMapper {
    public static UserDTO entityToDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus() );
        return userDTO;

    }
    public static UserDataDTO entityToData( UserEntity user, PersonDTO personDTO){
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setId(personDTO.getId());
        userDataDTO.setName(personDTO.getName());
        userDataDTO.setLastname(personDTO.getLastname());
        userDataDTO.setAddress(personDTO.getAddress());
        userDataDTO.setPhone(personDTO.getPhone());
        userDataDTO.setEmail(personDTO.getEmail());
        userDataDTO.setUsername(user.getUsername());
        userDataDTO.setRoles(user.getRole());
        userDataDTO.setCi(personDTO.getCi());
        userDataDTO.setStatus(user.getStatus());
        return userDataDTO;
    }
    public static UserCreateDTO entityToCreate( UserEntity user, PersonDTO personDTO) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setName(personDTO.getName());
        userCreateDTO.setLastname(personDTO.getLastname());
        userCreateDTO.setAddress(personDTO.getAddress());
        userCreateDTO.setPhone(personDTO.getPhone());
        userCreateDTO.setEmail(personDTO.getEmail());
        userCreateDTO.setUsername(user.getUsername());
        userCreateDTO.setRoles(user.getRole());
        return userCreateDTO;
    }

}
