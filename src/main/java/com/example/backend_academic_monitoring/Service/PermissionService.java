package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PermissionCreateDTO;
import com.example.backend_academic_monitoring.DTO.PermissionDTO;
import com.example.backend_academic_monitoring.DTO.RejectedPermissionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PermissionService {
    void savePermission(PermissionCreateDTO permissionCreateDTO, MultipartFile[] image);

    List<PermissionDTO> getAllPermissions();

    List<PermissionDTO> getPermissionStatus(Integer statusId);

    void approvePermission(Integer permissionId);

    void rejectPermission(RejectedPermissionDTO rejectedPermission);

    PermissionDTO getPermission(Integer permissionId);

    List<PermissionDTO> getPermissionsByClass(Integer classId);

    List<PermissionDTO> getPermissionByStudents(List<Integer> studentId, Integer year);
}
