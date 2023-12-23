package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.FatherDTO;
import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Entity.FatherEntity;
import com.example.backend_academic_monitoring.Repository.FatherRepository;
import com.example.backend_academic_monitoring.Service.FatherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatherServiceImpl implements FatherService {

    private FatherRepository fatherRepository;

    public FatherServiceImpl(FatherRepository fatherRepository) {
        this.fatherRepository = fatherRepository;
    }
    @Override
    public void saveFather(PersonDTO fatherDTO, Integer userId) {
        FatherEntity fatherEntity = new FatherEntity();
        fatherEntity.setName(fatherDTO.getName());
        fatherEntity.setLastname(fatherDTO.getLastname());
        fatherEntity.setAddress(fatherDTO.getAddress());
        fatherEntity.setEmail(fatherDTO.getEmail());
        fatherEntity.setPhone(fatherDTO.getPhone());
        fatherEntity.setStatus(fatherDTO.getStatus());
        fatherEntity.setUserId(userId);
        fatherEntity.setStatus(1);
        fatherRepository.save(fatherEntity);
    }

    @Override
    public void deleteFather(Integer userId) {
        FatherEntity fatherEntity = fatherRepository.findByUserId(userId);
        fatherEntity.setStatus(0);
        fatherRepository.save(fatherEntity);
    }

    @Override
    public void updateFather(PersonDTO personDTO) {
        FatherEntity fatherEntity = fatherRepository.findByUserId(personDTO.getId());
        fatherEntity.setName(personDTO.getName());
        fatherEntity.setLastname(personDTO.getLastname());
        fatherEntity.setAddress(personDTO.getAddress());
        fatherEntity.setPhone(personDTO.getPhone());
        fatherEntity.setEmail(personDTO.getEmail());
        fatherRepository.save(fatherEntity);

    }

    @Override
    public FatherDTO getFather() {
        return null;
    }

    @Override
    public List<FatherDTO> getAllFather() {
        return null;
    }

    public FatherDTO findFatherByUserId(Integer userId) {
        FatherEntity fatherEntity = fatherRepository.findByUserId(userId);
        FatherDTO fatherDTO = new FatherDTO();
        fatherDTO.setName(fatherEntity.getName());
        fatherDTO.setLastname(fatherEntity.getLastname());
        fatherDTO.setAddress(fatherEntity.getAddress());
        fatherDTO.setEmail(fatherEntity.getEmail());
        fatherDTO.setPhone(fatherEntity.getPhone());
        fatherDTO.setStatus(fatherEntity.getStatus());
        return fatherDTO;
    }
}
