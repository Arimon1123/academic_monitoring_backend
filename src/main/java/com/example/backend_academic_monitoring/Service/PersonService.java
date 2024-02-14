package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;

import java.util.List;

public interface PersonService {
    public List<PersonDTO> getAll();
    public PersonDTO getById(Integer id);
    public void save(PersonDTO personDTO, Integer userId);
    public void update(PersonDTO personDTO);
    public void delete(Integer id);
    public PersonDTO findByCi(String ci);
}
