package com.example.backend_academic_monitoring.Implementations;

import com.example.backend_academic_monitoring.DTO.PermissionCreateDTO;
import com.example.backend_academic_monitoring.DTO.PermissionDTO;
import com.example.backend_academic_monitoring.DTO.RejectedPermissionDTO;
import com.example.backend_academic_monitoring.Entity.ImageEntity;
import com.example.backend_academic_monitoring.Entity.PermissionEntity;
import com.example.backend_academic_monitoring.Entity.RejectedPermissionEntity;
import com.example.backend_academic_monitoring.Repository.PermissionRepository;
import com.example.backend_academic_monitoring.Repository.RejectPermissionRepository;
import com.example.backend_academic_monitoring.Service.ImageService;
import com.example.backend_academic_monitoring.Service.PermissionService;
import com.example.backend_academic_monitoring.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    public static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
    private final PermissionRepository permissionRepository;
    private final ImageService imageService;
    private final StudentService studentService;
    private final RejectPermissionRepository rejectPermissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository, ImageService imageService, StudentService studentService, RejectPermissionRepository rejectPermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.imageService = imageService;
        this.studentService = studentService;

        this.rejectPermissionRepository = rejectPermissionRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void savePermission(PermissionCreateDTO permissionCreateDTO, MultipartFile[] images) {

        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setDate(permissionCreateDTO.getDate());
        permissionEntity.setPermissionStartDate(permissionCreateDTO.getPermissionStartDate());
        permissionEntity.setPermissionEndDate(permissionCreateDTO.getPermissionEndDate());
        permissionEntity.setReason(permissionCreateDTO.getReason());
        permissionEntity.setStatus(1);
        permissionEntity.setPermissionStatus(0);
        permissionEntity.setStudentId(permissionCreateDTO.getStudentId());
        List<ImageEntity> imageEntities = imageService.saveFiles(images);
        permissionEntity.setImages(imageEntities);
        permissionRepository.save(permissionEntity);
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        List<PermissionEntity> permissionEntities = permissionRepository.findAll();
        return getPermissionDTOS(permissionEntities);
    }

    @Override
    public List<PermissionDTO> getPermissionStatus(Integer statusId) {
        List<PermissionEntity> permissionEntities =
                permissionRepository.findAllByPermissionStatusAndDateAfter(statusId, new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000)));
        return getPermissionDTOS(permissionEntities);
    }

    private List<PermissionDTO> getPermissionDTOS(List<PermissionEntity> permissionEntities) {
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (PermissionEntity permissionEntity : permissionEntities) {
            permissionDTOS.add(mapDTO(permissionEntity));
        }
        return permissionDTOS;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void approvePermission(Integer permissionId) {
        PermissionEntity permissionEntity = permissionRepository.findById(permissionId).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );
        permissionEntity.setPermissionStatus(1);
        permissionRepository.save(permissionEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void rejectPermission(RejectedPermissionDTO rejectedPermission) {
        PermissionEntity permissionEntity = permissionRepository.findById(rejectedPermission.getPermissionId()).orElseThrow(
                () -> new RuntimeException("Permission not found")
        );
        permissionEntity.setPermissionStatus(2);
        permissionRepository.save(permissionEntity);
        RejectedPermissionEntity rejectedPermissionEntity1 = new RejectedPermissionEntity();
        rejectedPermissionEntity1.setPermission(permissionEntity);
        rejectedPermissionEntity1.setReason(rejectedPermission.getReason());
        rejectPermissionRepository.save(rejectedPermissionEntity1);
    }

    @Override
    public PermissionDTO getPermission(Integer permissionId) {
        PermissionEntity permissionEntity = permissionRepository.getReferenceById(permissionId);
        return mapDTO(permissionEntity);

    }

    @Override
    public List<PermissionDTO> getPermissionsByClass(Integer classId) {
        List<PermissionEntity> permissionEntities =
                permissionRepository.findAllByClassIdAndDate(classId, new Timestamp(System.currentTimeMillis()), 1);
        return getPermissionDTOS(permissionEntities);

    }

    @Override
    public List<PermissionDTO> getPermissionByStudents(List<Integer> studentId, Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        List<PermissionEntity> permissionEntities = permissionRepository.findAllByStudentIds(studentId, timestamp);
        return getPermissionDTOS(permissionEntities);
    }

    private PermissionDTO mapDTO(PermissionEntity permissionEntity) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permissionEntity.getId());
        permissionDTO.setDate(permissionEntity.getDate());
        permissionDTO.setPermissionStartDate(permissionEntity.getPermissionStartDate());
        permissionDTO.setPermissionEndDate(permissionEntity.getPermissionEndDate());
        permissionDTO.setPermissionStatus(permissionEntity.getPermissionStatus());
        permissionDTO.setReason(permissionEntity.getReason());
        permissionDTO.setStudent(studentService.getStudent(permissionEntity.getStudentId()));
        List<String> images = new ArrayList<>();
        for (ImageEntity imageEntity : permissionEntity.getImages()) {
            images.add(imageService.getImageURL(imageEntity.getUuid()));
        }
        permissionDTO.setRejection(permissionEntity.getRejectedPermissionEntity());
        permissionDTO.setImages(images);
        logger.info("Permission DTO: {}", permissionEntity);
        return permissionDTO;
    }
}
