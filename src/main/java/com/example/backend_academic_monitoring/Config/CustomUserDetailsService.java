package com.example.backend_academic_monitoring.Config;


import com.example.backend_academic_monitoring.DTO.UserDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	

	private final UserRepository userDao;
	private final PasswordEncoder bcryptEncoder;
	private final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	public CustomUserDetailsService(UserRepository userDao, PasswordEncoder bcryptEncoder) {
		this.userDao = userDao;
		this.bcryptEncoder = bcryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;
		UserEntity user = userDao.findByUsernameAndStatus(username,1);
		LOGGER.info("{}",user);
		if (user != null) {
			roles = user.getRole().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
			LOGGER.info("{}",roles);
			return new User(user.getUsername(), user.getPassword(), roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + username);	}
	
	public UserEntity save(UserDTO user) {
		UserEntity newUser = new UserEntity();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		return userDao.save(newUser);
	}
}
