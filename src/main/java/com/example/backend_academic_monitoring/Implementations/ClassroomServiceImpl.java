package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ClassroomDTO;
import com.example.backend_academic_monitoring.Entity.ClassroomEntity;
import com.example.backend_academic_monitoring.Mappers.ClassroomMapper;
import com.example.backend_academic_monitoring.Repository.ClassroomRepository;
import com.example.backend_academic_monitoring.Service.ClassroomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    public static final Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;

    }

    @Override
    public List<ClassroomDTO> getClassroomsByRequirement(List<Integer> requirements) {
        if(!requirements.isEmpty()){
            List<ClassroomEntity> foundClassrooms;
            List<ClassroomEntity> classroomEntities = classroomRepository.findByRequirements(requirements.get(0));
            List<ClassroomEntity> finalClassrooms = new ArrayList<>();
            if(requirements.size() >1 ){
                for(int i = 1 ; i < requirements.size() ; i ++ ){
                    foundClassrooms = classroomRepository.findByRequirements(i);
                    for(ClassroomEntity classroom : foundClassrooms){
                        if(classroomEntities.contains(classroom)){
                            finalClassrooms.add(classroom);
                        }
                    }
                    classroomEntities = new ArrayList<>(finalClassrooms);
                    finalClassrooms.clear();
                }

            }
            return classroomEntities.stream().map(ClassroomMapper::toDTO).toList();

        }



        return null;
    }

    @Override
    public List<ClassroomDTO> getAllClassrooms(List<Integer> requirements) {
        return classroomRepository.findAll().stream().map(ClassroomMapper::toDTO).toList();
    }

    @Override
    public String getClassroomName(Integer classroomId) {
        ClassroomEntity classroom = classroomRepository.getReferenceById(classroomId);
        return classroom.getType() + " " + classroom.getBlock() + "-" + classroom.getNumber();
    }



}
