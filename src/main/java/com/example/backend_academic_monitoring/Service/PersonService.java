package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {
    public List<PersonDTO> getAll();
    public PersonEntity getById(Integer id);
    public PersonEntity save(PersonDTO personDTO, Integer userId);
    public void update(PersonDTO personDTO, Integer userId);
    public void delete(Integer id);
    public PersonDTO findByCi(String ci);
    public Page<PersonEntity> findAllByNameOrCI(String name, String lastname, String ci, String role,Integer page, Integer size);
    public List<PersonEntity> findAllByRole(String role);
    public boolean existsByCi(String ci);
    public boolean existsByEmail(String email);
    public boolean existsByPhone(String phone);

}
