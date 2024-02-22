package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Repository.PersonRepository;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    public static Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
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
    public PersonDTO getById(Integer id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        return personEntity.map(PersonMapper::entityToDTO).orElse(null);
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
    public List<PersonDTO> findAllByNameAndRole(String name, String role) {
        LOGGER.info("Buscando personas por nombre y rol {} y {} ", name, role);
        return personRepository.findAllByNameAndRole(name, role).stream().map(PersonMapper::entityToDTO).toList();
    }

    @Override
    public List<PersonEntity> findAllByRole(String role) {
        return personRepository.findAllByRole(role);
    }
}
