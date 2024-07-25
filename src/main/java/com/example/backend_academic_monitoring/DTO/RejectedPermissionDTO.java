package com.example.backend_academic_monitoring.DTO;

import lombok.Data;

@Data
public class RejectedPermissionDTO {
    private Integer id;
    private Integer permissionId;
    private String reason;
}
