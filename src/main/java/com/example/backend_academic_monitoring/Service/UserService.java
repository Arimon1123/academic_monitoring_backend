package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;

import java.util.List;

public interface UserService {
    public String saveUser(UserCreateDTO userCreateDTO);
    public void deleteUser(Integer id);
    public void updateUser(Integer id, String username, String password, String role);
    public void getUser(Integer id);
    public List<UserCreateDTO> getAllUser();
}
