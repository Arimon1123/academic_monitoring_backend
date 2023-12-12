package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody UserCreateDTO userDTO) {
        LOGGER.info("DTO {}",userDTO);
        return userService.saveUser(userDTO);
    }

    @GetMapping()
    public List<UserCreateDTO> getUser() {
        return userService.getAllUser();
    }
}
