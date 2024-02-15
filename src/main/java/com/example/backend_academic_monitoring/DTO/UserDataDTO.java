package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@ToString
public class UserDataDTO extends PersonDTO {
    private String imageUrl;
    private String username;
    private String role;
    private String ci;
}
