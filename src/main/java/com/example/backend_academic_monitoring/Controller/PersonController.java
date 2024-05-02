package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.DTO.PersonDTO;
import com.example.backend_academic_monitoring.DTO.ResponseDTO;
import com.example.backend_academic_monitoring.Mappers.PersonMapper;
import com.example.backend_academic_monitoring.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
                                        @RequestParam(defaultValue = "10", required = false) Integer size) {
        return personService.findAllByNameOrCI(name, lastname, ci, role, page, size).map(PersonMapper::entityToDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/ci/{ci}")
    public ResponseEntity<ResponseDTO<Boolean>> getByCi(@PathVariable String ci) {
        return ResponseEntity.ok(new ResponseDTO<>(personService.existsByCi(ci), "", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(new ResponseDTO<>(personService.existsByEmail(email), "", 200));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
    @GetMapping("/exists/phone/{phone}")
    public ResponseEntity<ResponseDTO<Boolean>> existsByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(new ResponseDTO<>(personService.existsByPhone(phone), "", 200));
    }
}
