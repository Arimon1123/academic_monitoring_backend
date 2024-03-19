package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.UserCreateDTO;
import com.example.backend_academic_monitoring.DTO.UserDTO;
import com.example.backend_academic_monitoring.DTO.UserDataDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Entity.UserEntity;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Mappers.UserMapper;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import com.example.backend_academic_monitoring.Service.*;
import com.example.backend_academic_monitoring.Utilities.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;


@Service
public class UserServiceImpl implements UserService {
    public static final String TEACHER_ROLE = "TEACHER";
    public static final String PARENT_ROLE = "PARENT";
    public static final String ADMINISTRATIVE_ROLE = "ADMINISTRATIVE";
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final PasswordGenerator passwordGenerator;
    private final EmailService emailService;
    private final PersonService personService;
    private final AdministrativeService administrativeService;
    private final ParentService parentService;
    private final TeacherService teacherService;
    private final ImageService fileService;
    @Value("${server.host}")
    private final String HOST = "192.168.0.181";
    @Value("${server.port}")
    private final String PORT = "8080";

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, PasswordGenerator passwordGenerator, EmailService emailService, PersonService personService, AdministrativeService administrativeService, ParentService parentService, TeacherService teacherService, ImageService fileService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.emailService = emailService;
        this.personService = personService;
        this.administrativeService = administrativeService;
        this.parentService = parentService;
        this.teacherService = teacherService;
        this.fileService = fileService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(UserCreateDTO userCreateDTO, MultipartFile image) {
        if (personService.existsByCi(userCreateDTO.getCi())) {
            throw new RuntimeException("La cedula ya existe");
        }
        if (personService.existsByEmail(userCreateDTO.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }
        if (personService.existsByPhone(userCreateDTO.getPhone())) {
            throw new RuntimeException("El telefono ya existe");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userCreateDTO.getCi());
        String generatedPassword = passwordGenerator.generatePassword();
        userEntity.setPassword(bCryptPasswordEncoder.encode(generatedPassword));
        userEntity.setRole(userCreateDTO.getRoles());
        userEntity.setStatus(1);
        if (image != null) {
            Integer imageId = fileService.saveFile(image).getId();
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
        PersonEntity personEntity = personService.save(personDTO, userEntity.getId());
        LOGGER.info("roles {} ", userCreateDTO.getRoles());
        if (userCreateDTO.getRoles().get(0).getName().equals(ADMINISTRATIVE_ROLE)) {
            administrativeService.save(personEntity);
            LOGGER.info("Administrative saved");
        }
        if (userCreateDTO.getRoles().get(0).getName().equals(TEACHER_ROLE)) {
            teacherService.save(personEntity, userCreateDTO.getAcademicEmail());
            LOGGER.info("Teacher saved");
        }
        if (userCreateDTO.getRoles().get(0).getName().equals(PARENT_ROLE)) {
            parentService.save(personEntity);
            LOGGER.info("Father saved");

        }
        Context context = new Context();
        context.setVariable("password", generatedPassword);
        context.setVariable("username", userCreateDTO.getCi());
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
    public void updateUser(UserCreateDTO userCreateDTO) {
        PersonEntity personEntity = personService.getById(userCreateDTO.getId());
        UserEntity userEntity = userRepository.findById(personEntity.getUserId()).orElseThrow();
        userEntity.setRole(userCreateDTO.getRoles());
        userRepository.save(userEntity);
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setName(userCreateDTO.getName());
        personDTO.setLastname(userCreateDTO.getLastname());
        personDTO.setAddress(userCreateDTO.getAddress());
        personDTO.setPhone(userCreateDTO.getPhone());
        personDTO.setEmail(userCreateDTO.getEmail());
        personDTO.setCi(userCreateDTO.getCi());
        personService.save(personDTO, userEntity.getId());

    }

    @Override
    public void blockUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setStatus(2);
        userRepository.save(userEntity);
    }

    @Override
    public void unblockUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setStatus(1);
        userRepository.save(userEntity);
    }

    @Override
    public UserDataDTO getUser(Integer id) {
        UserEntity user = userRepository.findById(id).orElseThrow();
        PersonDTO personDTO;
        personDTO = PersonMapper.entityToDTO(personService.getById(user.getId()));
        LOGGER.info("PersonDTO: {}, UserEntity {}", personDTO.getId(), user.getId());
        UserDataDTO userDataDTO = UserMapper.entityToData(user, personDTO);
        if (user.getImageId() != null) {
            String uuid = fileService.getImage(user.getImageId()).getUuid();
            userDataDTO.setImageUrl("http://" + HOST + ":" + PORT + "/file/image/" + uuid);
        }
        LOGGER.info(String.valueOf(user));
        return userDataDTO;
    }


    @Override
    public Page<UserDataDTO> searchUser(String name, String lastname, String role, String ci, Integer page, Integer size) {
        Page<PersonEntity> personList = personService.findAllByNameOrCI(name, lastname, ci, role, page, size);
        LOGGER.info("PersonList: {}", personList.getContent());
        return personList.map(person -> {
            UserEntity user = userRepository.findById(person.getUserId()).orElseThrow();
            UserDataDTO userDataDTO = UserMapper.entityToData(user, PersonMapper.entityToDTO(person));
            if (user.getImageId() != null) {
                String uuid = fileService.getImage(user.getImageId()).getUuid();
                userDataDTO.setImageUrl("http://" + HOST + ":" + PORT + "/file/image/" + uuid);
            }
            return userDataDTO;
        });
    }

    @Override
    public void updateUserImage(Integer id, MultipartFile image) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        fileService.deleteImage(userEntity.getImageId());
        Integer imageId = fileService.saveFile(image).getId();
        userEntity.setImageId(imageId);
        userRepository.save(userEntity);
    }

    @Override
    public boolean isUsernameAvaiable(String username) {
        return !userRepository.existsByUsername(username);
    }


    public UserDTO getUserByUsername(String username) {
        return UserMapper.entityToDTO(userRepository.findByUsername(username));
    }

    @Override
    public UserEntity getUserByPersonId(Integer id) {
        return null;
    }

    @Override
    public Object getUserRoleDetails(String username, String role) {
        UserDTO user = this.getUserByUsername(username);
        Object a = null;
        if (!user.getRole().stream().filter(roleEntity -> role.equals(roleEntity.getName())).toList().isEmpty()) {
            if (role.equals(PARENT_ROLE)) {
                a = parentService.getParentByUserId(user.getId());
            }
            if (role.equals(TEACHER_ROLE)) {
                a = teacherService.findTeacherByUserId(user.getId());
            }
            if (role.equals(ADMINISTRATIVE_ROLE)) {
                a = administrativeService.findByUserId(user.getId());
            }
        }

        return a;
    }


}
