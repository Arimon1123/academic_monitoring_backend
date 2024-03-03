package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.Entity.FatherEntity;
import com.example.backend_academic_monitoring.Entity.PersonEntity;
import com.example.backend_academic_monitoring.Repository.FatherRepository;
import com.example.backend_academic_monitoring.Service.FatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FatherServiceImpl implements FatherService {
    private final FatherRepository fatherRepository;
    @Autowired
    public FatherServiceImpl(FatherRepository fatherRepository) {
        this.fatherRepository = fatherRepository;
    }

    @Override
    public void save(PersonEntity personEntity) {
        FatherEntity fatherEntity = new FatherEntity();
        fatherEntity.setPerson(personEntity);
        fatherEntity.setStatus(1);
        fatherRepository.save(fatherEntity);

    }

    @Override
    public FatherEntity getFather(Integer id) {
        return fatherRepository.getReferenceById(id);
    }

}
