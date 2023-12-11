package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String role;
}
