package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    public Page<PersonDTO> searchPerson(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String lastname,
                                  @RequestParam(required = false) String ci,
                                  @RequestParam(required = false) String role,
                                  @RequestParam(defaultValue = "0", required = false) Integer page,
                                  @RequestParam(defaultValue = "10", required = false) Integer size){
        return personService.findAllByNameOrCI(name, lastname,ci,role,page,size).map(PersonMapper::entityToDTO);
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/ci/{ci}")
    public boolean getByCi(@PathVariable String ci){
        return personService.existsByCi(ci);
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/email/{email}")
    public boolean existsByEmail(@PathVariable String email){
        return personService.existsByEmail(email);
    }
    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/phone/{phone}")
    public boolean existsByPhone(@PathVariable String phone){
        return personService.existsByPhone(phone);
    }
}
