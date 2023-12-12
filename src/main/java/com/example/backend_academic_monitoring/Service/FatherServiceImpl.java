package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.FatherDTO;
import com.example.backend_academic_monitoring.Entity.FatherEntity;
import com.example.backend_academic_monitoring.Repository.FatherRepository;
import org.springframework.stereotype.Service;

@Service
public class FatherServiceImpl {

    private FatherRepository fatherRepository;

    public FatherServiceImpl(FatherRepository fatherRepository) {
        this.fatherRepository = fatherRepository;
    }

    public void saveFather(FatherDTO fatherDTO, Integer userId) {
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
