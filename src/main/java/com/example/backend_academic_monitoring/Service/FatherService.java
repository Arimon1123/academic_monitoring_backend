package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.FatherDTO;
import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.FatherEntity;

import java.util.List;

public interface FatherService {
    public void saveFather(PersonDTO fatherDTO, Integer userId);
    public void deleteFather(Integer userId);
    public void updateFather(PersonDTO personDTO);
    public FatherDTO getFather();
    public List<FatherDTO> getAllFather();
    public FatherDTO findFatherByUserId(Integer userId);
}
