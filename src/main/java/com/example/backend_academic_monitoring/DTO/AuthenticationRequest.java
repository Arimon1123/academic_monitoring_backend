package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	private String username;
	private String password;

}
