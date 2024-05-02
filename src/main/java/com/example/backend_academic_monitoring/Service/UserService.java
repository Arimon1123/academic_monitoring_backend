package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    public String saveUser(UserCreateDTO userCreateDTO, MultipartFile image, List<SubjectDTO> subjects);

    public void deleteUser(Integer id);

    public void updateUser(UserCreateDTO userCreateDTO);

    public void blockUser(String username);

    public void unblockUser(String username);

    public UserDataDTO getUser(Integer id);

    public Page<UserDataDTO> searchUser(String name, String lastname, String role, String ci, Integer page, Integer size);

    public void updateUserImage(Integer id, MultipartFile image);

    public boolean isUsernameAvaiable(String username);

    public UserDTO getUserByUsername(String username);

    public UserDTO getUserByPersonId(Integer id);

    public UserDetailsDTO getUserRoleDetails(String username, String role);

}
