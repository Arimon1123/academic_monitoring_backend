package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.Entity.GradeEntity;
import com.example.backend_academic_monitoring.Repository.GradeRepository;
import com.example.backend_academic_monitoring.Service.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public void save(GradeDTO gradeEntity) {

    }

    @Override
    public void update(GradeDTO gradeEntity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<GradeDTO> getAll() {
        return null;
    }

    @Override
    public GradeEntity getById(Integer id) {
        return gradeRepository.getReferenceById(id);
    }

    @Override
    public List<GradeDTO> getByTeacher(Integer teacherId) {
        return null;
    }
}
