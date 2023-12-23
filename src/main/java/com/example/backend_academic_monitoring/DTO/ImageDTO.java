package com.example.backend_academic_monitoring.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private Integer id;
    private String uuid;
    private String type;
    private String name;
}
