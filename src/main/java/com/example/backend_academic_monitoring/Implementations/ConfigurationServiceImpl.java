package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ClassDTO;
import com.example.backend_academic_monitoring.DTO.GradeDTO;
import com.example.backend_academic_monitoring.DTO.UnfinishedSubjectDTO;
import com.example.backend_academic_monitoring.Entity.ConfigEntity;
import com.example.backend_academic_monitoring.Repository.ConfigurationRepository;
import com.example.backend_academic_monitoring.Repository.ValidationRepository;
import com.example.backend_academic_monitoring.Service.ClassService;
import com.example.backend_academic_monitoring.Service.ConfigurationService;
import com.example.backend_academic_monitoring.Service.GradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationServiceImpl.class);
    private final ValidationRepository validationRepository;
    private final ConfigurationRepository configurationRepository;
    private final GradeService gradeService;
    private final ClassService classService;

    public ConfigurationServiceImpl(ValidationRepository validationRepository, ConfigurationRepository configurationRepository, GradeService gradeService, ClassService classService) {
        this.validationRepository = validationRepository;
        this.configurationRepository = configurationRepository;
        this.gradeService = gradeService;
        this.classService = classService;
    }

    @Override
    public List<UnfinishedSubjectDTO> finishBimester(Integer bimester, Integer year) {
        List<UnfinishedSubjectDTO> list = validateBimester(bimester, year);
        if (list.isEmpty()) {
            ConfigEntity config = configurationRepository.getReferenceById(1);
            config.setCurrentBimester(bimester + 1);
            configurationRepository.save(config);
        }
        return list;
    }

    private List<UnfinishedSubjectDTO> validateBimester(Integer bimester, Integer year) {
        List<Object[]> subjects = validationRepository.findUnterminatedSubjects(bimester, year);
        if (subjects.isEmpty()) {
            return List.of();
        }
        return subjects.stream().map(subject -> {
            UnfinishedSubjectDTO unfinishedSubjectDTO = new UnfinishedSubjectDTO();
            unfinishedSubjectDTO.setBimester((Integer) subject[0]);
            unfinishedSubjectDTO.setSubjectId((Integer) subject[1]);
            unfinishedSubjectDTO.setSubjectName((String) subject[2]);
            unfinishedSubjectDTO.setAssignationId((Integer) subject[3]);
            unfinishedSubjectDTO.setTeacherName((String) subject[4]);
            unfinishedSubjectDTO.setTeacherLastName((String) subject[5]);
            unfinishedSubjectDTO.setGradeNumber((String) subject[6]);
            unfinishedSubjectDTO.setSection((String) subject[7]);
            unfinishedSubjectDTO.setIdentifier((String) subject[8]);
            unfinishedSubjectDTO.setShift((Integer) subject[9]);
            return unfinishedSubjectDTO;
        }).toList();
    }

    @Override
    public void finishYear(Integer year) {
        int bimester = 1;
        for (int i = 1; i <= 4; i++) {
            List<UnfinishedSubjectDTO> list = validateBimester(bimester, year);
            if (!list.isEmpty()) {
                throw new RuntimeException("There are unfinished subjects");
            }
            bimester++;
        }
        ConfigEntity config = configurationRepository.getReferenceById(1);
        config.setCurrentYear(year + 1);
        configurationRepository.save(config);
        List<GradeDTO> grades = gradeService.getAll();
        List<String> identifiers = List.of("A", "B", "C", "D", "E");
        for (GradeDTO grade : grades) {
            for (String identifier : identifiers) {
                ClassDTO classDTO = new ClassDTO();
                classDTO.setGradeId(grade.getId());
                classDTO.setYear(year + 1);
                classDTO.setShift(1);
                classDTO.setIdentifier(identifier);
                classService.saveClass(classDTO);
            }
        }

    }

    @Override
    public ConfigEntity getCurrentConfig() {
        ConfigEntity config = configurationRepository.findById(1).orElseThrow();
        log.info("aa {} ", config);
        return config;
    }

}
