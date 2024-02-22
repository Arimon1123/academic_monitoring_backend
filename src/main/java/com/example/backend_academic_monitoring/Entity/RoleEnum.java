package com.example.backend_academic_monitoring.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



public enum RoleEnum {
    TEACHER,
    FATHER,
    ADMINISTRATIVE;
}