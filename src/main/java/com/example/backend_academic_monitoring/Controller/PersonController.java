package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{name}/{role}")
    public List<PersonDTO> getAll(@PathVariable String name, @PathVariable String role) {
        return personService.findAllByNameAndRole(name, role);
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
