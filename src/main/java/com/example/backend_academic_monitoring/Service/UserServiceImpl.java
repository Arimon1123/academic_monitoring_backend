package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.DTO.FatherDTO;
import com.example.backend_academic_monitoring.DTO.TeacherDTO;
import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
;import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements  UserService{
    public static final String TEACHER_ROLE = "TEACHER";
    public static final String FATHER_ROLE = "FATHER";
    public static final String  ADMINISTRATIVE_ROLE = "ADMINISTRATIVE";
    private final TeacherService teacherService;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final FatherServiceImpl fatherService;
    private final AdministrativeServiceImpl administrativeService;

    @Autowired
    public UserServiceImpl(TeacherService teacherService, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, FatherServiceImpl fatherService, AdministrativeServiceImpl administrativeService) {
        this.teacherService = teacherService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.fatherService = fatherService;
        this.administrativeService = administrativeService;
    }




    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String saveUser(UserCreateDTO userCreateDTO) {
        if(userRepository.existsByUsername(userCreateDTO.getUsername())){
            return "El usuario ya existe";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreateDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userCreateDTO.getPassword()));
        userEntity.setRole(userCreateDTO.getRole());
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        if(userCreateDTO.getRole().equals(TEACHER_ROLE)){
            TeacherDTO teacherEntity = new TeacherDTO();
            teacherEntity.setName(userCreateDTO.getName());
            teacherEntity.setLastname(userCreateDTO.getLastname());
            teacherEntity.setAddress(userCreateDTO.getAddress());
            teacherEntity.setPhone(userCreateDTO.getPhone());
            teacherEntity.setEmail(userCreateDTO.getEmail());
            teacherService.saveTeacher(teacherEntity, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(FATHER_ROLE)){
            FatherDTO fatherDTO = new FatherDTO();
            fatherDTO.setName(userCreateDTO.getName());
            fatherDTO.setLastname(userCreateDTO.getLastname());
            fatherDTO.setAddress(userCreateDTO.getAddress());
            fatherDTO.setPhone(userCreateDTO.getPhone());
            fatherDTO.setEmail(userCreateDTO.getEmail());
            fatherService.saveFather(fatherDTO, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(ADMINISTRATIVE_ROLE)){
            AdministrativeDTO userAdministrativeDTO = new AdministrativeDTO();
            userAdministrativeDTO.setName(userCreateDTO.getName());
            userAdministrativeDTO.setLastname(userCreateDTO.getLastname());
            userAdministrativeDTO.setAddress(userCreateDTO.getAddress());
            userAdministrativeDTO.setPhone(userCreateDTO.getPhone());
            userAdministrativeDTO.setEmail(userCreateDTO.getEmail());
            administrativeService.saveAdministrative(userAdministrativeDTO, userEntity.getId());
        }
        return "Usuario guardado correctamente";
    }
    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public void updateUser(Integer id, String username, String password, String role) {

    }

    @Override
    public void getUser(Integer id) {

    }

    @Override
    public List<UserCreateDTO> getAllUser() {
        return userRepository.findAll().stream().map(userEntity -> {
            UserCreateDTO userCreateDTO = new UserCreateDTO();
            if(userEntity.getRole().equals(TEACHER_ROLE)){
                TeacherDTO teacherDTO = teacherService.getTeacherByUserId(userEntity.getId());
                userCreateDTO.setName(teacherDTO.getName());
                userCreateDTO.setLastname(teacherDTO.getLastname());
                userCreateDTO.setAddress(teacherDTO.getAddress());
                userCreateDTO.setPhone(teacherDTO.getPhone());
                userCreateDTO.setEmail(teacherDTO.getEmail());
                userCreateDTO.setUsername(userEntity.getUsername());
                userCreateDTO.setRole(userEntity.getRole());
            }
            if(userEntity.getRole().equals(FATHER_ROLE)){
                FatherDTO fatherDTO = fatherService.findFatherByUserId(userEntity.getId());
                userCreateDTO.setName(fatherDTO.getName());
                userCreateDTO.setLastname(fatherDTO.getLastname());
                userCreateDTO.setAddress(fatherDTO.getAddress());
                userCreateDTO.setPhone(fatherDTO.getPhone());
                userCreateDTO.setEmail(fatherDTO.getEmail());
                userCreateDTO.setUsername(userEntity.getUsername());
                userCreateDTO.setRole(userEntity.getRole());
            }
            if(userEntity.getRole().equals(ADMINISTRATIVE_ROLE)){
                AdministrativeDTO administrativeDTO = administrativeService.findAdministrativeByUserId(userEntity.getId());
                System.out.println(administrativeDTO);
                userCreateDTO.setName(administrativeDTO.getName());
                userCreateDTO.setLastname(administrativeDTO.getLastname());
                userCreateDTO.setAddress(administrativeDTO.getAddress());
                userCreateDTO.setPhone(administrativeDTO.getPhone());
                userCreateDTO.setUsername(userEntity.getUsername());
                userCreateDTO.setEmail(administrativeDTO.getEmail());
                userCreateDTO.setRole(userEntity.getRole());
            }
            return userCreateDTO;
        }).toList();
    }


}
