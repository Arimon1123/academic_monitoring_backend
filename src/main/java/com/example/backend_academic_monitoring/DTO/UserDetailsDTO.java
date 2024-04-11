package com.example.backend_academic_monitoring.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailsDTO {
    private Object details;
    private UserDTO user;
    private List<StudentDTO> students;
    private List<ClassAssignationDTO> classAssignations;
    private String role;
}
