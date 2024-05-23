package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Entity.RoleEntity;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    String saveUserRole(UserCreateDTO userCreateDTO, MultipartFile image, List<SubjectDTO> subjects, List<ConsultHourDTO> consultHours);

    UserEntity saveUser(UserEntity user, MultipartFile image, String email);

    void saveStudentUser(StudentCreateDTO studentCreateDTO);

    void deleteUser(Integer id);

    void updateUser(UserCreateDTO userCreateDTO);

    void blockUser(String username);

    void unblockUser(String username);

    void updateUserPassword(String username, String password);

    void updateUserRole(String username, List<RoleEntity> roles);

    UserDataDTO getUser(Integer id);

    Page<UserDataDTO> searchUser(String name, String lastname, String role, String ci, Integer page, Integer size);

    void updateUserImage(Integer id, MultipartFile image);

    boolean isUsernameAvailable(String username);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByPersonId(Integer id);

    UserDetailsDTO getUserRoleDetails(String username, String role, Integer year);

    UserDTO getUserByAssignationId(Integer assignationId);
}
