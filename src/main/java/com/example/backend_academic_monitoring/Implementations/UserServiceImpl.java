package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.*;
import com.example.backend_academic_monitoring.Entity.*;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Mappers.UserMapper;
import com.example.backend_academic_monitoring.Repository.UserRepository;
import com.example.backend_academic_monitoring.Service.*;
import com.example.backend_academic_monitoring.Utilities.PasswordGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public static final String PARENT_ROLE = "PARENT";
    public static final String ADMINISTRATIVE_ROLE = "ADMINISTRATIVE";
    public static final String STUDENT_ROLE = "STUDENT";
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
    private final StudentService studentService;
    private final ClassAssignationService classAssignationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, PasswordGenerator passwordGenerator, EmailService emailService, PersonService personService, AdministrativeService administrativeService, ParentService parentService, TeacherService teacherService, ImageService fileService, StudentService studentService, ClassAssignationService classAssignationService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordGenerator = passwordGenerator;
        this.emailService = emailService;
        this.personService = personService;
        this.administrativeService = administrativeService;
        this.parentService = parentService;
        this.teacherService = teacherService;
        this.fileService = fileService;
        this.studentService = studentService;
        this.classAssignationService = classAssignationService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUserRole(UserCreateDTO userCreateDTO, MultipartFile image, List<SubjectDTO> subjects, List<ConsultHourDTO> consultHours) {
        if (personService.existsByCi(userCreateDTO.getCi())) throw new RuntimeException("La cedula ya existe");

        if (personService.existsByEmail(userCreateDTO.getEmail())) throw new RuntimeException("El email ya existe");

        if (personService.existsByPhone(userCreateDTO.getPhone())) throw new RuntimeException("El telefono ya existe");

        UserEntity user = new UserEntity();
        user.setUsername(userCreateDTO.getCi());
        user.setStatus(1);
        user.setRole(userCreateDTO.getRoles());
        user = saveUser(user, image, userCreateDTO.getEmail());

        PersonEntity personEntity = getPersonEntity(userCreateDTO, user);

        if (userCreateDTO.getRoles().get(0).getName().equals(ADMINISTRATIVE_ROLE)) {
            administrativeService.save(personEntity);
        }
        if (userCreateDTO.getRoles().get(0).getName().equals(TEACHER_ROLE)) {
            teacherService.save(personEntity, userCreateDTO.getAcademicEmail(), subjects, consultHours);
        }
        if (userCreateDTO.getRoles().get(0).getName().equals(PARENT_ROLE)) {
            parentService.save(personEntity);
        }

        return "Usuario guardado correctamente";
    }

    private PersonEntity getPersonEntity(UserCreateDTO userCreateDTO, UserEntity user) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(userCreateDTO.getName());
        personDTO.setLastname(userCreateDTO.getLastname());
        personDTO.setAddress(userCreateDTO.getAddress());
        personDTO.setPhone(userCreateDTO.getPhone());
        personDTO.setEmail(userCreateDTO.getEmail());
        personDTO.setCi(userCreateDTO.getCi());
        return personService.save(personDTO, user.getId());
    }

    @Override
    public UserEntity saveUser(UserEntity user, MultipartFile image, String email) {
        String generatedPassword = passwordGenerator.generatePassword();
        user.setPassword(bCryptPasswordEncoder.encode(generatedPassword));
        if (image != null) {
            Integer imageId = fileService.saveFile(image).getId();
            user.setImageId(imageId);
        }
        sendPasswordEmail(user, generatedPassword, email);
        return userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveStudentUser(StudentCreateDTO studentCreateDTO) {
        if (studentService.existsByCi(studentCreateDTO.getCi())) throw new RuntimeException("Ci ya existe");
        if (studentService.existsByRude(studentCreateDTO.getRude())) throw new RuntimeException("Rude ya existe");
        UserEntity user = new UserEntity();
        user.setUsername(studentCreateDTO.getCi());
        user.setStatus(1);
        user.setRole(List.of(new RoleEntity(4, STUDENT_ROLE)));
        user = saveUser(user, null, studentCreateDTO.getEmail());
        studentService.saveStudent(studentCreateDTO, user);
    }

    public void sendPasswordEmail(UserEntity user, String password, String email) {
        Context context = new Context();
        context.setVariable("password", password);
        context.setVariable("username", user.getUsername());
        emailService.sendPasswordEmail(email, "Contraseña generada", context);
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
        userEntity.setUsername(userCreateDTO.getCi());
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
    public void updateUserPassword(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUserRole(String username, List<RoleEntity> newRoles) {
        UserEntity userEntity = userRepository.findByUsername(username);
        PersonEntity personEntity = personService.findByUserId(userEntity.getId());
        List<RoleEntity> userRoles = userEntity.getRole();
        LOGGER.info("UserRoles: {}", userRoles);
        if (!userEntity.getRole().isEmpty()) {
            for (RoleEntity role : newRoles) {
                if (!userEntity.getRole().contains(role)) {
                    if (!updateRoleStatus(role, userEntity, 1)) {
                        createRole(role, personEntity);
                    }
                }
                LOGGER.info("Role: {}", role);
                userRoles.remove(role);
                LOGGER.info("UserRoles: {}", userRoles);
            }
            for (RoleEntity role : userRoles) {
                updateRoleStatus(role, userEntity, 0);
            }
        } else {
            for (RoleEntity role : newRoles) {
                if (!updateRoleStatus(role, userEntity, 1)) {
                    createRole(role, personEntity);
                }
            }
        }
        userEntity.setRole(newRoles);
        userRepository.save(userEntity);
    }

    private void createRole(RoleEntity role, PersonEntity personEntity) {
        if (role.getName().equals(TEACHER_ROLE)) {
            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setPerson(personEntity);
            teacherEntity.setStatus(1);
            teacherService.save(personEntity, "", null, null);
        }
        if (role.getName().equals(PARENT_ROLE)) {
            ParentEntity parent = new ParentEntity();
            parent.setStatus(1);
            parent.setPerson(personEntity);
            parentService.save(personEntity);
        }
        if (role.getName().equals(ADMINISTRATIVE_ROLE)) {
            AdministrativeEntity administrative = new AdministrativeEntity();
            administrative.setStatus(1);
            administrative.setPerson(personEntity);
            administrativeService.save(personEntity);
        }
    }

    private boolean updateRoleStatus(RoleEntity role, UserEntity user, Integer status) {
        if (role.getName().equals(TEACHER_ROLE)) {
            TeacherEntity teacherEntity = teacherService.findTeacherEntityByUserId(user.getId());
            if (teacherEntity != null) {
                teacherEntity.setStatus(status);
                teacherService.update(teacherEntity);
                return true;
            }
        }
        if (role.getName().equals(PARENT_ROLE)) {
            ParentEntity parent = parentService.getParentEntityByUserId(user.getId());
            if (parent != null) {
                parent.setStatus(status);
                parentService.update(parent);
                return true;
            }
        }
        if (role.getName().equals(ADMINISTRATIVE_ROLE)) {
            AdministrativeEntity administrative = administrativeService.findEntityByUserId(user.getId());
            if (administrative != null) {
                administrative.setStatus(status);
                administrativeService.update(administrative);
                return true;
            }
        }
        return false;
    }


    @Override
    public UserDataDTO getUser(Integer id) {
        UserEntity user = userRepository.findByPersonId(id);
        PersonDTO personDTO;
        personDTO = PersonMapper.entityToDTO(personService.getById(id));
        UserDataDTO userDataDTO = UserMapper.entityToData(user, personDTO);
        if (user.getImageId() != null) {
            String uuid = fileService.getImage(user.getImageId()).getUuid();
            fileService.getImageURL(uuid);
        }
        return userDataDTO;
    }


    @Override
    public Page<UserDataDTO> searchUser(String name, String lastname, String role, String ci, Integer page, Integer size) {
        Page<PersonEntity> personList = personService.findAllByNameOrCI(name, lastname, ci, role, page, size);

        return personList.map(person -> {
            UserEntity user = userRepository.findById(person.getUserId()).orElseThrow();
            UserDataDTO userDataDTO = UserMapper.entityToData(user, PersonMapper.entityToDTO(person));
            if (user.getImageId() != null) {
                String uuid = fileService.getImage(user.getImageId()).getUuid();
                userDataDTO.setImageUrl(fileService.getImageURL(uuid));
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
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }


    public UserDTO getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsernameAndStatus(username, 1);
        if (user == null) return null;
        return UserMapper.entityToDTO(user);
    }

    @Override
    public UserDTO getUserByPersonId(Integer id) {
        return UserMapper.entityToDTO(userRepository.findByPersonId(id));
    }

    @Override
    public UserDetailsDTO getUserRoleDetails(String username, String role, Integer year) {
        UserDTO user = this.getUserByUsername(username);
        UserDetailsDTO userDetails = new UserDetailsDTO();
        ObjectMapper mapper = new ObjectMapper();
        if (!user.getRole().stream().filter(roleEntity -> role.equals(roleEntity.getName())).toList().isEmpty()) {
            if (role.equals(PARENT_ROLE)) {
                userDetails.setDetails(parentService.getParentDTOByUserId(user.getId()));
            }
            if (role.equals(TEACHER_ROLE)) {
                userDetails.setDetails(teacherService.findTeacherDTOByUserId(user.getId()));
            }
            if (role.equals(ADMINISTRATIVE_ROLE)) {
                userDetails.setDetails(administrativeService.findDTOByUserId(user.getId()));
            }
            if (role.equals(STUDENT_ROLE)) {
                userDetails.setStudentDetails(studentService.findByUserId(user.getId()));
            }
        } else {
            throw new RuntimeException("El usuario no tiene el rol solicitado");
        }
        userDetails.setUser(user);
        if (role.equals(PARENT_ROLE)) {
            ParentDTO parent = mapper.convertValue(userDetails.getDetails(), ParentDTO.class);
            userDetails.setStudents(studentService.findAllByParentId(parent.getId()));
        }
        if (role.equals(TEACHER_ROLE)) {
            TeacherDTO teacher = mapper.convertValue(userDetails.getDetails(), TeacherDTO.class);
            userDetails.setClassAssignations(classAssignationService.getClassAssignationByTeacherId(teacher.getId(), year));
        }
        userDetails.setRole(role);
        return userDetails;
    }

    @Override
    public UserDTO getUserByAssignationId(Integer assignationId) {
        return UserMapper.entityToDTO(userRepository.findByAssignationId(assignationId));
    }


}
