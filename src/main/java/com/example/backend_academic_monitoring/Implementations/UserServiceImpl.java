package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Controller.FileController;
import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import com.example.backend_academic_monitoring.Service.ImageService;
import com.example.backend_academic_monitoring.Service.TeacherService;
import com.example.backend_academic_monitoring.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    public static final String TEACHER_ROLE = "TEACHER";
    public static final String FATHER_ROLE = "FATHER";
    public static final String  ADMINISTRATIVE_ROLE = "ADMINISTRATIVE";
    private final TeacherService teacherService;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final FatherServiceImpl fatherService;
    private final AdministrativeServiceImpl administrativeService;
    private final ImageService fileService;
    @Value("SERVER_HOST")
    private final String HOST = "localhost";
    @Value("SERVER_PORT")
    private final String PORT = "8080";
    public  static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(TeacherService teacherService, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, FatherServiceImpl fatherService, AdministrativeServiceImpl administrativeService, ImageService fileService) {
        this.teacherService = teacherService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.fatherService = fatherService;
        this.administrativeService = administrativeService;
        this.fileService = fileService;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public String saveUser(UserCreateDTO userCreateDTO, MultipartFile image) {
        if(userRepository.existsByUsername(userCreateDTO.getUsername())){
            return "El usuario ya existe";
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreateDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userCreateDTO.getPassword()));
        userEntity.setRole(userCreateDTO.getRole());
        userEntity.setStatus(1);
        if(image != null){
            Integer imageId = fileService.saveFile(image);
            userEntity.setImageId(imageId);
        }
        userEntity = userRepository.save(userEntity);
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(userCreateDTO.getName());
        personDTO.setLastname(userCreateDTO.getLastname());
        personDTO.setAddress(userCreateDTO.getAddress());
        personDTO.setPhone(userCreateDTO.getPhone());
        personDTO.setEmail(userCreateDTO.getEmail());
        if(userCreateDTO.getRole().equals(TEACHER_ROLE)){
            teacherService.saveTeacher(personDTO, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(FATHER_ROLE)){
            fatherService.saveFather(personDTO, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(ADMINISTRATIVE_ROLE)){
            administrativeService.saveAdministrative(personDTO, userEntity.getId());
        }
        return "Usuario guardado correctamente";
    }
    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userEntity.setStatus(0);
        userRepository.save(userEntity);
        if(userEntity.getRole().equals(TEACHER_ROLE)){
            teacherService.deleteTeacher(userEntity.getId());
        }
        if(userEntity.getRole().equals(FATHER_ROLE)){
            fatherService.deleteFather(userEntity.getId());
        }
        if(userEntity.getRole().equals(ADMINISTRATIVE_ROLE)){
            administrativeService.deleteAdministrative(userEntity.getId());
        }
    }

    @Override
    public void updateUser(UserCreateDTO userCreateDTO){
        UserEntity userEntity = userRepository.findById(userCreateDTO.getId()).orElseThrow();
        userEntity.setUsername(userCreateDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userCreateDTO.getPassword()));
        userEntity.setRole(userCreateDTO.getRole());
        userEntity.setStatus(1);
        userRepository.save(userEntity);
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(userCreateDTO.getName());
        personDTO.setLastname(userCreateDTO.getLastname());
        personDTO.setAddress(userCreateDTO.getAddress());
        personDTO.setPhone(userCreateDTO.getPhone());
        personDTO.setEmail(userCreateDTO.getEmail());
        if(userCreateDTO.getRole().equals(TEACHER_ROLE)){
            teacherService.saveTeacher(personDTO, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(FATHER_ROLE)){
            fatherService.saveFather(personDTO, userEntity.getId());
        }
        if(userCreateDTO.getRole().equals(ADMINISTRATIVE_ROLE)){
            administrativeService.saveAdministrative(personDTO, userEntity.getId());
        }
    }

    @Override
    public void blockUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setStatus(2);
        userRepository.save(userEntity);
    }

    @Override
    public UserDataDTO getUser(Integer id) {
        UserDataDTO userDataDTO = new UserDataDTO();
        UserEntity user = userRepository.findById(id).orElseThrow();
        PersonDTO personDTO;
        personDTO = getPersonDTO(user);
        entityToData(userDataDTO, user, personDTO);
        if(user.getImageId() != null){
            String uuid = fileService.getImage(user.getImageId()).getUuid();
            userDataDTO.setImageUrl( "http://"+HOST+":"+PORT+"/file/image/" + uuid);
        }
        return userDataDTO;
    }

    private void entityToData(UserDataDTO userDataDTO, UserEntity user, PersonDTO personDTO){
        userDataDTO.setName(personDTO.getName());
        userDataDTO.setLastname(personDTO.getLastname());
        userDataDTO.setAddress(personDTO.getAddress());
        userDataDTO.setPhone(personDTO.getPhone());
        userDataDTO.setEmail(personDTO.getEmail());
        userDataDTO.setUsername(user.getUsername());
        userDataDTO.setRole(user.getRole());

    }
    private void entityToCreate(UserCreateDTO userCreateDTO, UserEntity user, PersonDTO personDTO) {
        userCreateDTO.setName(personDTO.getName());
        userCreateDTO.setLastname(personDTO.getLastname());
        userCreateDTO.setAddress(personDTO.getAddress());
        userCreateDTO.setPhone(personDTO.getPhone());
        userCreateDTO.setEmail(personDTO.getEmail());
        userCreateDTO.setUsername(user.getUsername());
        userCreateDTO.setRole(user.getRole());
    }


    @Override
    public List<UserDataDTO> getAllUser() {
        return userRepository.findAll().stream().map(userEntity -> {
            UserDataDTO userDataDTO = new UserDataDTO();
            PersonDTO personDTO;
            personDTO = getPersonDTO(userEntity);

            entityToData(userDataDTO, userEntity, personDTO);
            if(userEntity.getImageId() != null){
                ImageDTO image = fileService.getImage(userEntity.getImageId());
                userDataDTO.setImageUrl("http://"+ HOST +":"+ PORT + "/file/image/" + image.getUuid());
            }
                return userDataDTO;
        }).toList();
    }

    @Override
    public void updateUserImage(Integer id, MultipartFile image) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        fileService.deleteImage(userEntity.getImageId());
        Integer imageId = fileService.saveFile(image);

        userEntity.setImageId(imageId);
        userRepository.save(userEntity);
    }

    @Override
    public boolean isUsernameAvaiable(String username) {
        return !userRepository.existsByUsername(username);
    }

    private PersonDTO getPersonDTO(UserEntity userEntity) {
        PersonDTO personDTO = new PersonDTO();
        if(userEntity.getRole().equals(TEACHER_ROLE)){
            personDTO = teacherService.getTeacherByUserId(userEntity.getId());
        }
        if(userEntity.getRole().equals(FATHER_ROLE)){
            personDTO = fatherService.findFatherByUserId(userEntity.getId());
        }
        if(userEntity.getRole().equals(ADMINISTRATIVE_ROLE)){
            personDTO = administrativeService.findAdministrativeByUserId(userEntity.getId());
        }
        return personDTO;
    }

}
