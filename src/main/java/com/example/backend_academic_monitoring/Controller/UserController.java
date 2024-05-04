package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<ResponseDTO<String>> saveUser(@RequestParam("user") String userDTO,
                                                        @RequestParam(value = "image", required = false) MultipartFile image,
                                                        @RequestParam(value = "subjects", required = false) String subjects,
                                                        @RequestParam(value = "consultHours", required = false) String consultHours) {
        if (image != null)
            LOGGER.info("DTO {}, image {}, type {}", userDTO, image.getOriginalFilename(), image.getContentType());
        try {
            UserCreateDTO userDto = objectMapper.readValue(userDTO, UserCreateDTO.class);
            List<SubjectDTO> subjectDTOList = objectMapper.readValue(subjects, new TypeReference<>() {
            });
            List<ConsultHourDTO> consultHourDTOS = null;
            if (consultHours != null) {
                consultHourDTOS = objectMapper.readValue(consultHours, new TypeReference<>() {
                });
            }
            return ResponseEntity.ok(
                    new ResponseDTO<>(
                            userService.saveUser(userDto, image, subjectDTOList, consultHourDTOS),
                            "Usuario creado",
                            200));
        } catch (Exception e) {
            LOGGER.info("Error al crear el usuario", e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            e.getMessage(),
                            401));
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping()
    public ResponseEntity<ResponseDTO<Page<UserDataDTO>>> getUsers(@RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String lastname,
                                                                   @RequestParam(required = false) String ci,
                                                                   @RequestParam(required = false) String role,
                                                                   @RequestParam(defaultValue = "0", required = false) Integer page,
                                                                   @RequestParam(defaultValue = "10", required = false) Integer size) {
        try {
            return ResponseEntity.ok(
                    new ResponseDTO<>(
                            userService.searchUser(name, lastname, role, ci, page, size),
                            "Usuarios obtenidos",
                            200));
        } catch (Exception e) {
            LOGGER.error("Error al obtener los usuarios", e);

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
    public ResponseEntity<ResponseDTO<String>> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile image) {
        try {
            userService.updateUserImage(id, image);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar la imagen", e);
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
                        200));
    }

    @GetMapping("/{username}/available")
    public ResponseEntity<ResponseDTO<Boolean>> existsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        userService.isUsernameAvaiable(username),
                        "Usuario disponible",
                        200));
    }

    @PutMapping("/block/{username}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    public ResponseEntity<ResponseDTO<String>> blockUser(@PathVariable String username) {
        try {
            userService.blockUser(username);
        } catch (Exception e) {
            LOGGER.error("Error al bloquear el usuario", e);
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
                        200));
    }

    @PutMapping("/unblock/{username}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    public ResponseEntity<ResponseDTO<String>> unblockUser(@PathVariable String username) {
        try {
            userService.unblockUser(username);
        } catch (Exception e) {
            LOGGER.error("Error al bloquear el usuario", e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al desbloquear el usuario",
                            401));
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        null,
                        "Usuario desbloqueado",
                        200));
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    public ResponseEntity<ResponseDTO<String>> updateUser(@RequestBody() UserCreateDTO user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            LOGGER.error("Error al actualizar el usuario", e);
            return ResponseEntity.badRequest().body(
                    new ResponseDTO<>(
                            null,
                            "Error al actualizar el usuario",
                            401));
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(
                        null,
                        "Usuario actualizado",
                        200));
    }

    @PutMapping("/roles")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    public ResponseEntity<?> updateUseRoles(@RequestBody() UserDTO user) {
        try {
            userService.updateUserRole(user.getUsername(), user.getRole());
            return ResponseEntity.ok(new ResponseDTO<>(null, "Roles updated successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDTO<>(e.getMessage(), "Error trying to update roles", 200));
        }
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserByPersonId(@PathVariable Integer personId) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(userService.getUserByPersonId(personId), "User retrieved successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }

    @GetMapping("/details")
    public ResponseEntity<ResponseDTO<UserDetailsDTO>> getUserDetails(@RequestParam String username, @RequestParam String role) {
        try {
            return ResponseEntity.ok(new ResponseDTO<>(userService.getUserRoleDetails(username, role), "Details Retrieved Successfully", 200));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ResponseDTO<>(null, e.getMessage(), 500));
        }
    }
}
