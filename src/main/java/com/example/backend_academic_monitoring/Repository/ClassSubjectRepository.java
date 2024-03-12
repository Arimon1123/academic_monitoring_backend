package com.example.backend_academic_monitoring.Repository;

import com.example.backend_academic_monitoring.Entity.ClassSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassSubjectRepository extends JpaRepository<ClassSubjectEntity, Integer>{
    List<ClassSubjectEntity> findAllByClassId(Integer classId);
    List<ClassSubjectEntity> findAllByClassroomId(Integer subjectId);
}
