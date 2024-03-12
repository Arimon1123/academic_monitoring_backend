package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.ClassSubjectDTO;
import com.example.backend_academic_monitoring.DTO.ClassroomDTO;
import com.example.backend_academic_monitoring.Entity.ClassSubjectEntity;
import com.example.backend_academic_monitoring.Entity.ClassroomEntity;
import com.example.backend_academic_monitoring.Mappers.ClassroomMapper;
import com.example.backend_academic_monitoring.Repository.ClassSubjectRepository;
import com.example.backend_academic_monitoring.Repository.ClassroomRepository;
import com.example.backend_academic_monitoring.Service.ClassroomService;
import com.example.backend_academic_monitoring.Service.SubjectService;
import com.example.backend_academic_monitoring.Service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final ClassSubjectRepository classSubjectRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    public static final Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, ClassSubjectRepository classSubjectRepository, TeacherService teacherService, SubjectService subjectService) {
        this.classroomRepository = classroomRepository;
        this.classSubjectRepository = classSubjectRepository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
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
    public String getClassroomName(Integer classroomId) {
        ClassroomEntity classroom = classroomRepository.getReferenceById(classroomId);
        return classroom.getType() + " " + classroom.getBlock() + "-" + classroom.getNumber();
    }

    @Override
    public List<ClassSubjectDTO> getClassroomSubjects(Integer classroomId) {
        List<ClassSubjectEntity> classSubjectEntities = classSubjectRepository.findAllByClassroomId(classroomId);
        return classSubjectEntities.stream().map(
                classSubjectEntity -> {
                    ClassSubjectDTO classSubjectDTO = new ClassSubjectDTO();
                    classSubjectDTO.setId(classSubjectEntity.getId());
                    classSubjectDTO.setSubjectName(subjectService.getSubjectName(classSubjectEntity.getSubjectId()));
                    classSubjectDTO.setTeacherName(teacherService.getTeacherName(classSubjectEntity.getTeacherId()));
                    classSubjectDTO.setClassroomName(this.getClassroomName(classSubjectEntity.getClassroomId()));
                    classSubjectDTO.setSchedule(classSubjectEntity.getSchedules());
                    return classSubjectDTO;
                }
        ).toList();
    }


}
