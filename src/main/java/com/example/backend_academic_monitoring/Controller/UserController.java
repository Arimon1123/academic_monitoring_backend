package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
import com.example.backend_academic_monitoring.DTO.UserDataDTO;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final ObjectMapper objectMapper;

    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO<String>> saveUser(@RequestParam("user") String userDTO, @RequestParam(value = "image", required = false) MultipartFile image) {
        if(image != null) LOGGER.info("DTO {}, image {}, type {}", userDTO, image.getOriginalFilename(), image.getContentType());
        try {
            UserCreateDTO userDto = objectMapper.readValue(userDTO, UserCreateDTO.class);
             return ResponseEntity.ok(
                     new ResponseDTO<>(
                             userService.saveUser(userDto,image),
                             "Usuario creado",
                             200));
        }catch (Exception e){
            LOGGER.error("Error al convertir el DTO",e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al crear el usuario",
                            401));
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO<List<UserDataDTO>>> getUsers(@RequestParam(required = false) String role) {
        try {
            return ResponseEntity.ok(
                    new ResponseDTO<>(
                            userService.getAllUser(role),
                            "Usuarios obtenidos",
                            200));
        }catch (Exception e){
            LOGGER.error("Error al obtener los usuarios",e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al obtener los usuarios",
                            401));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDataDTO>> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        userService.getUser(id),
                        "Usuario obtenido",
                        200));
    }
    @PutMapping("/{id}/image")
    public ResponseEntity<ResponseDTO<String>> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image){
        try{
            userService.updateUserImage(id,image);
        }catch (Exception e){
            LOGGER.error("Error al actualizar la imagen",e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al actualizar la imagen",
                            401));
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        null,
                        "Imagen actualizada",
                        200 ));
    }
    @GetMapping("/{username}/available")
    public ResponseEntity<ResponseDTO<Boolean>> existsByUsername(@PathVariable String username){
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        userService.isUsernameAvaiable(username),
                        "Usuario disponible",
                        200));
    }
    @PutMapping("/block/{username}")
    public ResponseEntity<ResponseDTO<String>> blockUser(@PathVariable String username){
        try{
            userService.blockUser(username);
        }catch (Exception e){
            LOGGER.error("Error al bloquear el usuario",e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al bloquear el usuario",
                            401));
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        null,
                        "Usuario bloqueado",
                        200 ));
    }
}
