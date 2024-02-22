package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Mappers.UserMapper;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import com.example.backend_academic_monitoring.Service.EmailService;
import com.example.backend_academic_monitoring.Service.ImageService;
import com.example.backend_academic_monitoring.Service.PersonService;
import com.example.backend_academic_monitoring.Service.UserService;
import com.example.backend_academic_monitoring.Utilities.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    public static final String TEACHER_ROLE = "TEACHER";
    public static final String FATHER_ROLE = "FATHER";
    public static final String  ADMINISTRATIVE_ROLE = "ADMINISTRATIVE";
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final EmailService emailService;
    private final PersonService personService;
    private final ImageService fileService;
    @Value("SERVER_HOST")
    private final String HOST = "localhost";
    @Value("SERVER_PORT")
    private final String PORT = "8080";
    public  static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, PasswordGenerator passwordGenerator, EmailService emailService, PersonService personService, ImageService fileService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.emailService = emailService;
        this.personService = personService;
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
        String generatedPassword = passwordGenerator.generatePassword();
        userEntity.setPassword(bCryptPasswordEncoder.encode(generatedPassword));
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
        personDTO.setCi(userCreateDTO.getCi());
        personService.save(personDTO, userEntity.getId());
        Context context = new Context();
        context.setVariable("password", generatedPassword);
        emailService.sendPasswordEmail(userCreateDTO.getEmail(), "Contraseña generada", context);

        return "Usuario guardado correctamente";
    }
    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userEntity.setStatus(0);
        userRepository.save(userEntity);
        personService.delete(userEntity.getId());
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
        personService.delete(userEntity.getId());
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
        LOGGER.info("PersonDTO: {}, UserEntity {}", personDTO.getId(),user.getId() );
        entityToData(userDataDTO, user, personDTO);
        if(user.getImageId() != null){
            String uuid = fileService.getImage(user.getImageId()).getUuid();
            userDataDTO.setImageUrl( "http://"+HOST+":"+PORT+"/file/image/" + uuid);
        }
        return userDataDTO;
    }

    private void entityToData(UserDataDTO userDataDTO, UserEntity user, PersonDTO personDTO){
        userDataDTO.setId(personDTO.getId());
        userDataDTO.setName(personDTO.getName());
        userDataDTO.setLastname(personDTO.getLastname());
        userDataDTO.setAddress(personDTO.getAddress());
        userDataDTO.setPhone(personDTO.getPhone());
        userDataDTO.setEmail(personDTO.getEmail());
        userDataDTO.setUsername(user.getUsername());
        userDataDTO.setRole(user.getRole());
        userDataDTO.setCi(personDTO.getCi());

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
    public List<UserDataDTO> getAllUser(String role) {
        List<PersonEntity> personList = personService.findAllByRole(role);
        return personList.stream().map(person -> {
            UserDataDTO userDataDTO = new UserDataDTO();
            UserEntity user = userRepository.findById(person.getUserId()).orElseThrow();
            LOGGER.info("PersonDTO: {}, UserEntity {}", person.getId(),user.getId() );
            entityToData(userDataDTO, user, PersonMapper.entityToDTO(person));
            if(user.getImageId() != null){
                String uuid = fileService.getImage(user.getImageId()).getUuid();
                userDataDTO.setImageUrl( "http://"+HOST+":"+PORT+"/file/image/" + uuid);
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
        return personService.getById(userEntity.getId());
    }
    public UserDTO getUserByUsername(String username){
        return UserMapper.entityToDTO(userRepository.findByUsername(username));
    }
}
