package com.example.backend_academic_monitoring.DTO;

import lombok.*;
import org.springframework.core.io.Resource;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResourceDTO {
    private Resource resource;
    private ImageDTO imageDTO;
}
