package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Repository.PersonRepository;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    public static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDTO> getAll() {
        LOGGER.info("Buscando todas las personas");
        return personRepository.findAll().stream().map(PersonMapper::entityToDTO).toList();
    }

    @Override
    public PersonEntity getById(Integer id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Override
    public PersonEntity save(PersonDTO personDTO, Integer userId) {
        PersonEntity personEntity = PersonMapper.dtoToEntity(personDTO, userId, 1);
        return personRepository.save(personEntity);
    }

    @Override
    public void update(PersonDTO personDTO, Integer userId) {
        PersonEntity personEntity = PersonMapper.dtoToEntity(personDTO, userId, 1);
        personRepository.save(personEntity);
    }

    @Override
    public void delete(Integer id) {
        PersonEntity personEntity = personRepository.findById(id).orElseThrow();
        personEntity.setStatus(0);
        personRepository.save(personEntity);
    }

    @Override
    public PersonDTO findByCi(String ci) {
        PersonEntity personEntity = personRepository.findByCi(ci);
        return PersonMapper.entityToDTO(personEntity);
    }


    @Override
    public Page<PersonEntity> findAllByNameOrCI(String name, String lastname, String ci, String role, Integer page, Integer size) {
        if (name != null) name = "%" + name + "%";
        else name = "%%";
        if (lastname != null) lastname = "%" + lastname + "%";
        else lastname = "%%";
        if (ci != null) ci = "%" + ci + "%";
        else ci = "%%";
        if (role != null) role = "%" + role + "%";
        else role = "%%";
        LOGGER.info("Buscando personas por nombre: {}, apellido: {}, ci: {}, role: {}", name, lastname, ci, role);
        return personRepository.findAllByNameAndRole(name, lastname, ci, role, PageRequest.of(page, size));
    }

    @Override
    public List<PersonEntity> findAllByRole(String role) {
        if (role == null) return personRepository.findAll();
        return personRepository.findAllByRole(role);
    }

    @Override
    public boolean existsByCi(String ci) {
        return personRepository.existsByCi(ci);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return personRepository.existsByPhone(phone);
    }

    @Override
    public PersonEntity findByUserId(Integer userId) {
        return personRepository.findByUserId(userId);
    }
}
