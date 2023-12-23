package com.example.backend_academic_monitoring.Service;

import com.example.backend_academic_monitoring.DTO.AdministrativeDTO;
import com.example.backend_academic_monitoring.DTO.PersonDTO;

public interface AdministrativeService {
    public void saveAdministrative(PersonDTO administrativeDTO, Integer userId);
    public void deleteAdministrative(Integer id);
    public void updateAdministrative(PersonDTO administrativeDTO);
    public AdministrativeDTO getAdministrative(Integer id);
    public void getAllAdministrative();
    public AdministrativeDTO findAdministrativeByUserId(Integer userId);
}
