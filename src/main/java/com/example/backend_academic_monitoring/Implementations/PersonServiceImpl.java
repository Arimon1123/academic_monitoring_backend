package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Override
    public List<PersonDTO> getAll() {
        return null;
    }

    @Override
    public PersonDTO getById(Integer id) {
        return null;
    }

    @Override
    public void save(PersonDTO personDTO, Integer userId) {

    }

    @Override
    public void update(PersonDTO personDTO) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public PersonDTO findByCi(String ci) {
        return null;
    }
}
