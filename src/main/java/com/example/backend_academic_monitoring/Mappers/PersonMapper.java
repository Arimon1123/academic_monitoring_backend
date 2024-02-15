package com.example.backend_academic_monitoring.Mappers;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.PersonEntity;

public class PersonMapper {
    public static PersonDTO entityToDTO(PersonEntity personEntity){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setCi(personEntity.getCi());
        personDTO.setName(personEntity.getName());
        personDTO.setLastname(personEntity.getLastname());
        personDTO.setAddress(personEntity.getAddress());
        personDTO.setPhone(personEntity.getPhone());
        personDTO.setEmail(personEntity.getEmail());
        return personDTO;
    }
    public static  PersonEntity dtoToEntity(PersonDTO personDTO,  Integer userId, Integer status){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(personDTO.getId());
        personEntity.setCi(personDTO.getCi());
        personEntity.setName(personDTO.getName());
        personEntity.setLastname(personDTO.getLastname());
        personEntity.setAddress(personDTO.getAddress());
        personEntity.setPhone(personDTO.getPhone());
        personEntity.setEmail(personDTO.getEmail());
        personEntity.setStatus(status);
        personEntity.setUserId(userId);
        return personEntity;
    }
}
